package com.example.exampletest.service;

import com.example.exampletest.aop.TimeTrace;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {

    @TimeTrace
    public void print() {
        System.out.println("print() 실행");
    }
}
