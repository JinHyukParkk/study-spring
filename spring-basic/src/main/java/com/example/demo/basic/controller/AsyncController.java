package com.example.demo.basic.controller;

import com.example.demo.basic.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/async")
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    @GetMapping("/hello")
    public String hello() {
        asyncService.hello();
        log.info("method end");
        return "Hello";
    }

    @GetMapping("/hello1")
    public CompletableFuture hello1() {
        log.info("completable future init");
        return asyncService.run();
    }
}
