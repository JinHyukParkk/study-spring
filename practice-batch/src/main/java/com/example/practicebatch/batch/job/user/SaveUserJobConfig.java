package com.example.practicebatch.batch.job.user;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SaveUserJobConfig {

    public static final String SAVE_USER_JOB = "SAVE_USER_JOB";
    private final JobBuilderFactory jobBuilderFactory;
    private final Flow testSettingFlow;

    @Bean
    public Job userJob() {
        return this.jobBuilderFactory.get(SAVE_USER_JOB)
            .start(testSettingFlow)
            .build()
            .build();
    }
}
