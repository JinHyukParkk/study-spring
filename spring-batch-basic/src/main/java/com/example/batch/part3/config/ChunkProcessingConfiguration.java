package com.example.batch.part3.config;

import com.example.batch.part3.CustomItemReader;
import com.example.batch.part3.domain.Person;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class ChunkProcessingConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public ChunkProcessingConfiguration(JobBuilderFactory jobBuilderFactory,
                                        StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean

    public Job chunkProcessingJob() {
        return jobBuilderFactory.get("chunkProcessingJob")
                .incrementer(new RunIdIncrementer())
                .start(this.taskBaseStep())
                .next(this.chunkBaseStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step chunkBaseStep(@Value("#{jobParameters[chunkSize]}") String chunkSize) {
        return stepBuilderFactory.get("chunkBaseStep")
                .<String, String>chunk(StringUtils.isNotEmpty(chunkSize) ? Integer.parseInt(chunkSize) : 10)
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    private ItemWriter<String> itemWriter() {
        return items -> log.info("chunk item size : {}", items.size());
//        return items -> items.forEach(log::info);
    }

    private ItemProcessor<String, String> itemProcessor() {
        return item -> item + ", Spring Batch";
    }

    private ItemReader<String> itemReader() {
        return new ListItemReader<>(getItems());
    }

    @Bean
    public Step taskBaseStep() {
        return stepBuilderFactory.get("taskBaseStep")
                .tasklet(this.tasklet(null))
                .build();
    }

    @Bean
    @StepScope
    public Tasklet tasklet(@Value("#{jobParameters[chunkSize]}") String value) {
        List<String> items = getItems();

        return (contribution, chunkContext) -> {
            StepExecution stepExecution = contribution.getStepExecution();

            // 실행 시 옵션으로 --chunkSize=10 값 넣어줌
//            JobParameters jobParameters = stepExecution.getJobParameters();
//            String value = jobParameters.getString("chunkSize", "10");

            int chunkSize = StringUtils.isNotEmpty(value) ? Integer.parseInt(value) : 10;
            int fromIndex = stepExecution.getReadCount();
            int toIndex = fromIndex + chunkSize;

            if (fromIndex >= items.size()) {
                return RepeatStatus.FINISHED;
            }

            List<String> subList = items.subList(fromIndex, toIndex);

            stepExecution.setReadCount(toIndex);
            log.info("task item size : {}", subList.size());

            return RepeatStatus.CONTINUABLE;
        };
    }

    private List<String> getItems() {
        List<String> items = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            items.add(i + " Hello");
        }

        return items;
    }

    @Configuration
    @Slf4j
    public static class ItemWriterConfiguration {

        private final JobBuilderFactory jobBuilderFactory;
        private final StepBuilderFactory stepBuilderFactory;
        private final DataSource dataSource;
        private final EntityManagerFactory entityManagerFactory;

        public ItemWriterConfiguration(JobBuilderFactory jobBuilderFactory,
                                       StepBuilderFactory stepBuilderFactory,
                                       DataSource dataSource,
                                       EntityManagerFactory entityManagerFactory) {
            this.jobBuilderFactory = jobBuilderFactory;
            this.stepBuilderFactory = stepBuilderFactory;
            this.dataSource = dataSource;
            this.entityManagerFactory = entityManagerFactory;
        }

        @Bean
        public Job itemWriterJob() throws Exception {
            return this.jobBuilderFactory.get("itemWriterJob")
                    .incrementer(new RunIdIncrementer())
                    .start(this.csvItemWriterStep())
    //                .next(this.jdbcBatchItemWriterStep())
                    .next(this.jpaItemWriterStep())
                    .build();
        }

        @Bean
        public Step csvItemWriterStep() throws Exception {
            return stepBuilderFactory.get("csvItemWriterStep")
                    .<Person, Person>chunk(10)
                    .reader(itemReader())
                    .writer(csvFileItemWriter())
                    .build();
        }

        @Bean
        public Step jdbcBatchItemWriterStep() {
            return stepBuilderFactory.get("jdbcBatchItemWriterStep")
                    .<Person, Person>chunk(10)
                    .reader(itemReader())
                    .writer(jdbcBatchItemWriter())
                    .build();
        }

        @Bean
        public Step jpaItemWriterStep() {
            return stepBuilderFactory.get("jpaItemWriterStep")
                    .<Person, Person>chunk(10)
                    .reader(itemReader())
                    .writer(jpaItemWriter())
                    .build();
        }

        private ItemWriter<Person> jpaItemWriter() {
            return new JpaItemWriterBuilder<Person>()
                    .entityManagerFactory(entityManagerFactory)
    //                .usePersist(true)   // id를 할당할 경우 select 조회하고, insert 함 => 설정함으로써 select 안함
                    .build();
        }

        private ItemWriter<Person> jdbcBatchItemWriter() {
            JdbcBatchItemWriter itemWriter = new JdbcBatchItemWriterBuilder<Person>()
                    .dataSource(dataSource)
                    .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                    .sql("insert into person(name, age, address) values(:name, :age, :address)")
                    .build();

            itemWriter.afterPropertiesSet();

            return itemWriter;
        }

        private ItemWriter<Person> csvFileItemWriter() throws Exception {
            BeanWrapperFieldExtractor<Person> fieldExtractor = new BeanWrapperFieldExtractor<>();
            fieldExtractor.setNames(new String[] {"id", "name", "age", "address"});

            DelimitedLineAggregator<Person> lineAggregator = new DelimitedLineAggregator<>();
            lineAggregator.setDelimiter(",");
            lineAggregator.setFieldExtractor(fieldExtractor);

            FlatFileItemWriter<Person> itemWriter = new FlatFileItemWriterBuilder<Person>()
                    .name("csvFileItemWriter")
                    .encoding("UTF-8")
                    .resource(new FileSystemResource("output/test-output.csv"))
                    .lineAggregator(lineAggregator)
                    .headerCallback(writer -> writer.write("id,이름,나이,거주지"))
                    .footerCallback(writer -> writer.write("-----------------\n"))
    //                .append(true) // 덮어쓰지 않고 뒤에 이어서 씀
                    .build();

            itemWriter.afterPropertiesSet();

            return itemWriter;
        }

        private ItemReader<Person> itemReader() {
            return new CustomItemReader<>(getItems());
        }

        private List<Person> getItems() {
            List<Person> items = new ArrayList<>();

            for (int i = 0; i < 100; i++) {
                items.add(new Person("test name", "test age", "test address" + i));
            }

            return items;
        }
    }
}
