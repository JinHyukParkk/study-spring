package com.example.practicebatch.batch.flow.user;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SaveUserFlowConfig {

    private final Step saveUserSettingStep;

    private final Step testSettingStep;


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
}
