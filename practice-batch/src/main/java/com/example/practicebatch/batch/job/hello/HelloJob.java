package com.example.practicebatch.batch.job.hello;

import com.example.practicebatch.batch.step.hello.HelloStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HelloJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final Step printHelloStep;

    public HelloJob(JobBuilderFactory jobBuilderFactory,
                    Step printHelloStep) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.printHelloStep = printHelloStep;
    }

    @Bean
    public Job helleJob() {
        return jobBuilderFactory.get("helloJob")
                .start(printHelloStep)
                .build();

    }
}
