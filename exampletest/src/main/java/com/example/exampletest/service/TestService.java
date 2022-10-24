package com.example.exampletest.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    TestComponent testComponent;

    public TestService() {
        System.out.println("TestService 생성자 호출");
        try {
            testComponent.print();
        }catch (Exception e){
            System.out.println("생성자 단계에서 호출 실패");
        }
    }

    @PostConstruct
    public void execute() {
        System.out.println("TestService @PostConstruct 호출");

        try {
            testComponent.print();
        }catch (Exception e){
            System.out.println("@PostConstruct 단계에서 호출 실패");
        }
    }
}
