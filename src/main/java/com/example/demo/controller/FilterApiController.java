package com.example.demo.controller;

import com.example.demo.models.User1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/filter")
public class FilterApiController {

    @PostMapping("post")
    public User1 user(@RequestBody User1 user) {
        log.info("User : {}", user);

        return user;

    }
}
