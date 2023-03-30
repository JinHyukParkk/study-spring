package com.example.practicebatch.batch.step.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class HelloStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    public HelloStepConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean("printHelloStep")
    public Step printHelloStep() {
        return stepBuilderFactory.get("printHelloStep")
            .tasklet((contribution, chunkContext) -> {
                log.info("hello batch world.");
                return RepeatStatus.FINISHED;
            }).build();
    }

    @Bean("throwErrorStep")
    public Step throwErrorStep() {
        return stepBuilderFactory.get("throwErrorStep")
            .tasklet(((contribution, chunkContext) -> {
                log.error("throw error");
                contribution.setExitStatus(ExitStatus.FAILED);
                return RepeatStatus.FINISHED;
            })).build();
    }
}
