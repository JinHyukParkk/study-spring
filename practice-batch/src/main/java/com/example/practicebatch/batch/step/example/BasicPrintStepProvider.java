package com.example.practicebatch.batch.step.example;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
@RequiredArgsConstructor
public class BasicPrintStepProvider {

    private final StepBuilderFactory stepBuilderFactory;

    public Step print1Step() {
        return stepBuilderFactory.get("print1")
            .tasklet((stepContribution, chunkContext) -> {
                Thread.sleep(1000);
                System.out.println("print1Step(No Param) : No userId");
                return RepeatStatus.FINISHED;
            })
            .build();
    }

    public Step print2Step(String userId) {
        return stepBuilderFactory.get("print2")
            .tasklet((stepContribution, chunkContext) -> {
                Thread.sleep(1000);
                System.out.println("print2Step(Job Param) : " + userId);
                return RepeatStatus.FINISHED;
            })
            .build();
    }
}
