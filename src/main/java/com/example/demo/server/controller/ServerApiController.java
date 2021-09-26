package com.example.demo.server.controller;

import com.example.demo.basic.dto.UserRequest;
import com.example.demo.basic.models.User1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
public class ServerApiController {

    @GetMapping("/hello")
    public String hello() {
        return "hello server";
    }

    @GetMapping("/user")
    public User1 user(UserRequest userRequest) {
        User1 user = new User1();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        return user;
    }
}
