package com.example.practicebatch.batch.step.user;

import com.example.practicebatch.batch.tasklet.user.settingUserTasklet;
import com.example.practicebatch.domain.repository.UserRepository;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaveUserStepConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final UserRepository userRepository;

    public SaveUserStepConfig(StepBuilderFactory stepBuilderFactory,
                              UserRepository userRepository) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.userRepository = userRepository;
    }

    @Bean
    public Step saveUserSettingStep() {
        return this.stepBuilderFactory.get("saveUserSettingStep")
            .tasklet(new settingUserTasklet(userRepository))
            .build();
    }

    @Bean
    @JobScope
    public Step testSettingStep(@Value("#{jobParameters[userId]}") String userId) {
        System.out.println("testSettingStep : " + userId);
        return this.stepBuilderFactory.get("testSettingStep")
            .tasklet(new settingUserTasklet(userRepository))
            .build();
    }
}
