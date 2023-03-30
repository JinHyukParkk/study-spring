package com.example.practicebatch.batch.job.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HelloJobConfig {

    public static final String HELLO_JOB = "HELLO_JOB";
    private final JobBuilderFactory jobBuilderFactory;
    private final Step printHelloStep;
    private final Step throwErrorStep;

    public HelloJobConfig(JobBuilderFactory jobBuilderFactory,
                          Step printHelloStep,
                          Step throwErrorStep) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.printHelloStep = printHelloStep;
        this.throwErrorStep = throwErrorStep;
    }

    @Bean
    public Job helleJob() {
        return jobBuilderFactory.get("HELLO_JOB")
            .start(printHelloStep)
            .next(throwErrorStep)
            .build();

    }
}
