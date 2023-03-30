package com.example.practicebatch.batch.flow.example;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@RequiredArgsConstructor
public class BasicPrintFlowConfig {

    public static final String BASIC_PRINT_FLOW = "BASIC_PRINT_FLOW";

    private final Step print1;

    private final Step print2;

    private final Step print3;

    private final Step print4;

    @Bean
    public Flow basicPrintFlow() {
        System.out.println("Parallels 1, 4");
        return new FlowBuilder<Flow>(BASIC_PRINT_FLOW)
            .split(new SimpleAsyncTaskExecutor())
            .add(print1Flow(), print4Flow())
            .build();
    }

    @Bean
    @JobScope
    public Flow basicPrint14Flow(@Value("#{jobParameters[userId]}") String userId) {
        System.out.println("Parallels(JobParam) 1, 4 : " + userId);
        return new FlowBuilder<Flow>("BASIC_PRINT_FLOW")
            .split(new SimpleAsyncTaskExecutor())
            .add(print1Flow(), print4Flow())
            .build();
    }

    @Bean
    public Flow print1Flow() {
        return new FlowBuilder<Flow>("print1Flow")
            .start(print1)
            .build();
    }

    @Bean
    public Flow print2Flow() {
        return new FlowBuilder<Flow>("print2Flow")
            .start(print2)
            .build();
    }

    @Bean
    public Flow print3Flow() {
        return new FlowBuilder<Flow>("print3Flow")
            .start(print3)
            .build();
    }


    @Bean
    public Flow print4Flow() {
        return new FlowBuilder<Flow>("print4Flow")
            .start(print4)
            .build();
    }
}
