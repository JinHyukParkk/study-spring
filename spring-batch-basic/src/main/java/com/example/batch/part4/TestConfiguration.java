package com.example.batch.part4;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TestConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public TestConfiguration(JobBuilderFactory jobBuilderFactory,
                             StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job TestJob() {
        return this.jobBuilderFactory.get("TestJob")
                .incrementer(new RunIdIncrementer())
                .start(this.TestStep())
                .build();
    }

    @Bean
    public Step TestStep() {
        return this.stepBuilderFactory.get("TestStep")
                .chunk(10)
                .reader()
                .processor()
                .writer()
                .build();
    }
}
