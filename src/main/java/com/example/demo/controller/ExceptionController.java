package com.example.demo.controller;

import com.example.demo.models.User1;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/exception")
@Validated
public class ExceptionController {

    @GetMapping("/get")
    public User1 get(
            @Size(min = 2)
            @RequestParam String name,

            @NotNull
            @Min(1)
            @RequestParam Integer age) {
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
