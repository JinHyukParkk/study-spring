package com.example.practicebatch.example;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class BasicJobConfig {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private int index = 0;

    @Bean
    public Job basicJob() {
        return this.jobBuilderFactory.get("basicJob")
            .start(basicStep())
            .build();
    }

    public Step basicStep() {
        return this.stepBuilderFactory.get("basicStep")
            .<String, String>chunk(10)
            .reader(basicReader())
            .writer(basicWriter())
            .build();
    }

    private ListItemReader<String> basicReader() {
        System.out.println("reader");
        List<String> items = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            items.add("item : " + (i * index));
        }
        return new ListItemReader<>(items);
    }

    private ItemWriter<String> basicWriter() {
        return items -> {
            System.out.println("write");
            for (String item : items) {
                System.out.println("item = " + item);
            }
        };
    }
}
