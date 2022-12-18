package com.example.exampletest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ExampletestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampletestApplication.class, args);
    }

}
