package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostApiController {

    @PostMapping("/hello")   // http://localhost:9090/api/get/hello
    public String getHello() {
        return "get hello";
    }
}
