package com.example.demo.basic.controller;

import com.example.demo.basic.dto.PostRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
public class PostApiController {

    @PostMapping("")   // http://localhost:9090/api/post
//    public void post(@RequestBody Map<String, Object> requestData) {
    public void post(@RequestBody PostRequestDto postRequestDto) {
//        requestData.forEach((key, value) -> {
//            System.out.println("key : " + key);
//            System.out.println("value : " + value);
//
//        });

        System.out.println(postRequestDto.toString());

    }
}
