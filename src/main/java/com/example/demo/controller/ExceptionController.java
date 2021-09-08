package com.example.demo.controller;

import com.example.demo.models.User1;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/exception")
public class ExceptionController {

    @GetMapping("/get")
    public User1 get(@RequestParam(required = false) String name, @RequestParam(required = false) Integer age) {
        User1 user = new User1();
        user.setName(name);
        user.setAge(age);

        int a = 10 + user.getAge();

        return user;
    }

    @PostMapping("/post")
    public User1 post(@Valid @RequestBody User1 user) {

        return user;
    }
}
