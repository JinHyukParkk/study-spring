package com.example.practicebatch.batch.job.example;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BasicPrintJobConfig {

    public static final String FLOW_EXAMPLE_JOB = "BASIC_PRINT_JOB";

    private final JobBuilderFactory jobBuilderFactory;

    private final Step print2Step;

    private final Flow print2Flow;

    private final Flow basicParallelsPrintFlow;

    private final Flow basicParallelsPrintWithJobScopeFlow;

    @Bean
    public Job basicPrintJob() {
        return this.jobBuilderFactory.get(FLOW_EXAMPLE_JOB)
            .start(basicParallelsPrintFlow)
            .next(basicParallelsPrintWithJobScopeFlow)
            .next(print2Flow)
            .next(print2Step)
            .build()
            .build();
    }
}
