package com.example.exampletest.postcontruct.service;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    @PostConstruct
    public void start() {
        fixedDelayJob();
    }

    @Scheduled(fixedDelay = 1000)
    public void fixedDelayJob() {
        System.out.println("#### fixedDelayJob ####");
    }
}
