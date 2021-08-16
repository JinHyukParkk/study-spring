package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController // Rest API 를 처리하는 Controller 어노테이션
@RequestMapping("/api") // URI를 지정해주는 어노테이션
public class ApiController {

    @GetMapping("/hello")  // http://localhost:9090/api/hello
    public String hello() {
        return "hello spring boot";
    }
}
