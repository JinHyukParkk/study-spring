package com.example.practicebatch.batch.step.user;

import com.example.practicebatch.batch.tasklet.user.settingUserTasklet;
import com.example.practicebatch.domain.repository.UserRepository;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SaveUserStep {

    private final String STEP_NAME = "saveUser";
    private final StepBuilderFactory stepBuilderFactory;
    private final UserRepository userRepository;

    public SaveUserStep(StepBuilderFactory stepBuilderFactory,
                        UserRepository userRepository) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.userRepository = userRepository;
    }

    @Bean(STEP_NAME + "SettingStep")
    public Step saveUserSettingStep() {
        return this.stepBuilderFactory.get(STEP_NAME + "SettingStep")
                .tasklet(new settingUserTasklet(userRepository))
                .build();
    }
}
