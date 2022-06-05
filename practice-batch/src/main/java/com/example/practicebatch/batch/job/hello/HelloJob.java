package com.example.practicebatch.batch.job.hello;

import com.example.practicebatch.batch.step.hello.HelloStep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HelloJob {

    private final JobBuilderFactory jobBuilderFactory;
    private final HelloStep helloStep;

    public HelloJob(JobBuilderFactory jobBuilderFactory,
                    HelloStep helloStep) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.helloStep = helloStep;
    }

    @Bean
    public Job helleJob() {
        return jobBuilderFactory.get("helloJob")
                .start(helloStep.printHelloStep())
                .build();

    }
}
