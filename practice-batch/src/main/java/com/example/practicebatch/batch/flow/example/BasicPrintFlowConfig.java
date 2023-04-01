package com.example.practicebatch.batch.flow.example;

import com.example.practicebatch.batch.step.example.BasicPrintStepProvider;
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

    private final BasicPrintStepProvider basicPrintStepProvider;

    private final Step print1Step;

    private final Step print2Step;

    private final Step print3Step;

    private final Step print4Step;

    @Bean
    public Flow basicParallelsPrintFlow() {
        System.out.println("basicParallelsPrintFlow");
        return new FlowBuilder<Flow>(BASIC_PRINT_FLOW)
            .split(new SimpleAsyncTaskExecutor())
            .add(print1Flow(), print4Flow())
            .build();
    }

    @Bean
    @JobScope
    public Flow basicParallelsPrintWithJobScopeFlow(@Value("#{jobParameters[userId]}") String userId) {
        System.out.println("basicParallelsPrintWithJobScopeFlow");
        return new FlowBuilder<Flow>(BASIC_PRINT_FLOW)
            .split(new SimpleAsyncTaskExecutor())
            .add(print1FromProviderFlow(), print2FromProviderFlow(userId))
            .build();
    }

    public Flow print1FromProviderFlow() {
        return new FlowBuilder<Flow>("print1FromProviderFlow")
            .start(basicPrintStepProvider.print1Step())
            .build();
    }

    public Flow print2FromProviderFlow(String userId) {
        return new FlowBuilder<Flow>("print2FromProviderFlow")
            .start(basicPrintStepProvider.print2Step(userId))
            .build();
    }

    @Bean
    public Flow print1Flow() {
        return new FlowBuilder<Flow>("print1Flow")
            .start(print1Step)
            .build();
    }

    @Bean
    public Flow print2Flow() {
        return new FlowBuilder<Flow>("print2Flow")
            .start(print2Step)
            .build();
    }

    @Bean
    public Flow print3Flow() {
        return new FlowBuilder<Flow>("print3Flow")
            .start(print3Step)
            .build();
    }


    @Bean
    public Flow print4Flow() {
        return new FlowBuilder<Flow>("print4Flow")
            .start(print4Step)
            .build();
    }
}
