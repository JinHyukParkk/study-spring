package com.example.demo.server.controller;

import com.example.demo.server.dto.Req;
import com.example.demo.server.dto.UserRequest;
import com.example.demo.server.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/server")
public class ServerApiController {

    @GetMapping("/hello")
    public String hello() {
        return "hello server";
    }

    @GetMapping("/user")
    public User user(UserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setAge(userRequest.getAge());
        return user;
    }

    @PostMapping("/user/{userId}/name/{userName}")
    public Req<User> post(
                     // HttpEntity<String> entity,
                     @RequestBody Req<User> user,
                     @PathVariable int userId,
                     @PathVariable String userName,
                     @RequestHeader("x-authorization") String authorization,
                     @RequestHeader("custom-header") String customHeader
    ){
//        log.info("req : {}", entity.getBody());
        log.info("userId : {}, username : {}", userId, userName);
        log.info("authorization : {}, custom : {}", authorization, customHeader);
        log.info("client req : {}", user);

        Req<User> response = new Req<>();
        response.setHeader(
                new Req.Header()
        );
        response.setResponseBody(user.getResponseBody());

        return response;
    }
}
