package com.example.exampletest.postcontruct.config;

import com.example.exampletest.postcontruct.aop.TimeTraceAop;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class SpringConfig {

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }
}
