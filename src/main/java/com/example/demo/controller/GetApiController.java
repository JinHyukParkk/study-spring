package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
public class GetApiController {

    // http://localhost:9090/api/get/hello
    @GetMapping("/hello")
    public String hello() {
        return "get hello";
    }

    // http://localhost:9090/api/get/hi
    @RequestMapping(value = "/hi", method = RequestMethod.GET)  // 예전 방식
    public String hi() {
        return "hi";
    }

    // http://localhost:9090/api/get/path-variable/{}
    @GetMapping("/path-varible/{id}")
    public String parthVariable(@PathVariable(name = "id") String pathName) {
        System.out.println("PathVariable : " + pathName);

        return pathName;
    }

    // http://localhost:9090/api/get/query-param?user=pjh0819&email=pjh0819@naver.com&age=29
    @GetMapping("query-param")
    public String queryParam(@RequestParam Map<String, String> queryParam) {

        StringBuilder sb = new StringBuilder();

        queryParam.entrySet().forEach(entry -> {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("\n");

            sb.append(entry.getKey() + " = " + entry.getValue() + "\n");
        });

        return sb.toString();
    }
}
