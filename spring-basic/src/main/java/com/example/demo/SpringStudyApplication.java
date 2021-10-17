package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Base64;

@SpringBootApplication
@ServletComponentScan
//@EnableAsync
public class SpringStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringStudyApplication.class, args);
        System.out.println(Base64.getEncoder().encodeToString("pjh0819@naver.com".getBytes()));
    }
}
