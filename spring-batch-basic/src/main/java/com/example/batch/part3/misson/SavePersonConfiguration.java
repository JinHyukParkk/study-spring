package com.example.batch.part3.misson;

import com.example.batch.part3.domain.Person;
import com.example.batch.part3.exception.NotFoundNameException;
import com.example.batch.part3.listener.SavePersonListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.persistence.EntityManagerFactory;

@Configuration
@Slf4j
public class SavePersonConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    public SavePersonConfiguration(JobBuilderFactory jobBuilderFactory,
                                   StepBuilderFactory stepBuilderFactory,
                                   EntityManagerFactory entityManagerFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Bean
    public Job savePersonJob() throws Exception {
        return this.jobBuilderFactory.get("savePersonJob")
                .incrementer(new RunIdIncrementer())
                .start(this.savePersonStep(null))
                .listener(new SavePersonListener.SavePersonJobExcutionListener())
                .listener(new SavePersonListener.SavePersonAnnotationJobExecutionListener())
                .build();
    }

    @Bean
    @JobScope
    public Step savePersonStep(@Value("#{jobParameters[allow_duplicate]}") String allowDuplicate) throws Exception {
        return this.stepBuilderFactory.get("savePersonStep")
                .<Person, Person>chunk(10)
                .reader(itemReader())
//                .processor(new DuplicateValidationProcessor<>(Person::getName, Boolean.parseBoolean(allowDuplicate)))
                .processor(itmeProcessor(allowDuplicate))
                .writer(itemWriter())
                .listener(new SavePersonListener.SaverPersonStepExecutionListener())
                .faultTolerant()
                .skip(NotFoundNameException.class)
                .skipLimit(3)
                .build();
    }

    private ItemProcessor<? super Person,? extends Person> itmeProcessor(String allowDuplicate) throws Exception {
        DuplicateValidationProcessor<Person> personDuplicateValidationProcessor =
                new DuplicateValidationProcessor<>(Person::getName, Boolean.parseBoolean(allowDuplicate));

        ItemProcessor<Person, Person> validationProcessor = item -> {
            if (item.isNotEmptyName()) {
                return item;
            }

            try {
                throw new NotFoundNameException();
            } catch (NotFoundNameException e) {
                e.printStackTrace();
            }
        };

        CompositeItemProcessor<Person, Person> itemProcessor = new CompositeItemProcessorBuilder()
                .delegates(validationProcessor, personDuplicateValidationProcessor)
                .build();

        itemProcessor.afterPropertiesSet();

        return itemProcessor;


    }

    private ItemWriter<? super Person> itemWriter() throws Exception {
        JpaItemWriter<Person> jpaItemWriter = new JpaItemWriterBuilder<Person>()
                .entityManagerFactory(entityManagerFactory)
                .build();

        ItemWriter<Person> logItemWriter = items -> log.info("person.size : {}", items.size());

        CompositeItemWriter<Person> itemWriter = new CompositeItemWriterBuilder<Person>()
                .delegates(jpaItemWriter, logItemWriter)
                .build();

        itemWriter.afterPropertiesSet();

        return itemWriter;
//        return items -> items.forEach(item -> log.info(item.getName()));
    }

    private ItemReader<? extends Person> itemReader() throws Exception {
        DefaultLineMapper<Person> defaultLineMapper = new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setNames("name", "age", "address");
        defaultLineMapper.setLineTokenizer(lineTokenizer);

        defaultLineMapper.setFieldSetMapper(fieldSet -> new Person(
                fieldSet.readString(0),
                fieldSet.readString(1),
                fieldSet.readString(2)
        ));

        FlatFileItemReader<Person> savePersonItemReader = new FlatFileItemReaderBuilder<Person>()
                .name("savePersonItemReader")
                .encoding("UTF-8")
                .linesToSkip(1)
                .resource(new ClassPathResource("person.csv"))
                .lineMapper(defaultLineMapper)
                .build();

        savePersonItemReader.afterPropertiesSet();

        return savePersonItemReader;
    }
}
