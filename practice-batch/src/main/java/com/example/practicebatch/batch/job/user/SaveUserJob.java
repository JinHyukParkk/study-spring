package com.example.practicebatch.batch.job.user;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SaveUserJob {

    public static final String SERVICE_NAME = "USER_JOB";
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final Step saveUserSettingStep;
    private final Step testSettingStep;

    @Bean(SERVICE_NAME)
    public Job userJob() {
        return this.jobBuilderFactory.get(SERVICE_NAME)
            .start(step1Flow())
            .next(testSettingFlow())
            .build()
            .build();
    }

    @Bean
    @JobScope
    public Step step1(@Value("#{jobParameters[userId]}") String userId) {
        return stepBuilderFactory.get("step1")
            .tasklet((stepContribution, chunkContext) -> {
                Thread.sleep(1000);
                System.out.println("step1 : " + userId);
                return RepeatStatus.FINISHED;
            })
            .build();
    }

    @Bean
    public Flow saveUserSettingFlow() {
        return new FlowBuilder<Flow>("saveUserSettingFlow")
            .start(saveUserSettingStep)
            .build();
    }

    @Bean
    public Flow testSettingFlow() {
        return new FlowBuilder<Flow>("testSettingFlow")
            .start(testSettingStep)
            .build();
    }


    @Bean
    public Flow step1Flow() {
        return new FlowBuilder<Flow>("step1Flow")
            .start(step1(null))
            .build();
    }
}
