package com.example.exampletest.postcontruct.service;

import com.example.exampletest.postcontruct.aop.TimeTrace;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {

    @TimeTrace
    public void print() {
        System.out.println("print() 실행");
    }
}
