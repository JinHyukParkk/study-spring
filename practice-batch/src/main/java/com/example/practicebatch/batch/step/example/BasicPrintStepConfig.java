package com.example.practicebatch.batch.step.example;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BasicPrintStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step print1Step() {
        return stepBuilderFactory.get("print1")
            .tasklet((stepContribution, chunkContext) -> {
                Thread.sleep(1000);
                System.out.println("print1Step(No Param) : No userId");
                return RepeatStatus.FINISHED;
            })
            .build();
    }

    @Bean
    @JobScope
    public Step print2Step(@Value("#{jobParameters[userId]}") String userId) {
        return stepBuilderFactory.get("print2")
            .tasklet((stepContribution, chunkContext) -> {
                Thread.sleep(1000);
                System.out.println("print2Step(Job Param) : " + userId);
                return RepeatStatus.FINISHED;
            })
            .build();
    }

    @Bean
    @JobScope
    public Step print3Step(@Value("#{jobParameters[userId]}") String userId) {
        return stepBuilderFactory.get("print3")
            .tasklet((stepContribution, chunkContext) -> {
                Thread.sleep(1000);
                System.out.println("print3Step(Job Param) : " + userId);
                return RepeatStatus.FINISHED;
            })
            .build();
    }

    @Bean
    public Step print4Step() {
        System.out.println("print4Step");
        return stepBuilderFactory.get("print4")
            .tasklet(printTasklet(null))
            .build();
    }

    @Bean
    @StepScope
    public Tasklet printTasklet(@Value("#{jobParameters[userId]}") String userId) {
        return (stepContribution, chunkContext) -> {
            Thread.sleep(1000);
            System.out.println("printTasklet(Step Param) : " + userId);
            return RepeatStatus.FINISHED;
        };
    }
}
