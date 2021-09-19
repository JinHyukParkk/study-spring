package com.example.demo.controller;

import com.example.demo.annotation.Decode;
import com.example.demo.annotation.Timer;
import com.example.demo.models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/aop")
public class AopApiController {

    @GetMapping("/get/{id}")
    public String get(@PathVariable Long id, @RequestParam String name) {
        System.out.println("get method");

        return id + " " + name;

    }

    @PostMapping("/post")
    public User post(@RequestBody User user) {
        System.out.println("post method : " + user);

        return user;
    }

    @Timer
    @DeleteMapping("/delete")
    public void delete() throws InterruptedException {

        Thread.sleep(1000 * 2);
    }

    @Decode
    @PutMapping("/put")
    public User put(@RequestBody User user) {
        System.out.println("put");
        System.out.println(user);

        return user;
    }
}
