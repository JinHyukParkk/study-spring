package com.example.demo.client.controller;

import com.example.demo.client.service.RestTemplateService;
import com.example.demo.basic.dto.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientApiController {

    private final RestTemplateService restTemplateService;

    public ClientApiController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    @GetMapping("/hello")
    public String getHello() {
        return restTemplateService.hello();
    }

    @GetMapping("/user")
    public UserResponse getUser() {
        return restTemplateService.user();
    }
}
