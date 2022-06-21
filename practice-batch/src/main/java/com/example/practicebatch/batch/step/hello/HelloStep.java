package com.example.practicebatch.batch.step.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HelloStep {

    private final StepBuilderFactory stepBuilderFactory;

    public HelloStep(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean(name = "printHelloStep")
    public Step printHelloStep() {
        return stepBuilderFactory.get("printHelloStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("hello batch world.");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
