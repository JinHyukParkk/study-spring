package com.example.practicebatch.batch.job.user;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaveUserJob {

    private final String SERVICE_NAME = "user";
    private final JobBuilderFactory jobBuilderFactory;
    private final Step saveUserSettingStep;

    public SaveUserJob(JobBuilderFactory jobBuilderFactory,
                       Step saveUserSettingStep) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.saveUserSettingStep = saveUserSettingStep;
    }

    @Bean(SERVICE_NAME + "Job")
    public Job userJob() {
        return this.jobBuilderFactory.get(SERVICE_NAME + "Job")
                .start(saveUserSettingStep)
                .build();
    }
}
