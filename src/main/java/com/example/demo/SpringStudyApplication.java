package com.example.demo;

import com.example.demo.ioc.ApplicationContextProvider;
import com.example.demo.ioc.Base64Encoder;
import com.example.demo.ioc.Encoder;
import com.example.demo.ioc.UrlEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@SpringBootApplication
public class SpringStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringStudyApplication.class, args);
        System.out.println(Base64.getEncoder().encodeToString("pjh0819@naver.com".getBytes()));
    }
}
