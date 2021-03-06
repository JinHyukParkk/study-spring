package com.example.demo.server.controller;

import com.example.demo.server.dto.Req;
import com.example.demo.server.dto.UserRequest;
import com.example.demo.server.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.Charset;

@Api(tags = {"Server 에서 사용하는 API"})  // swagger
@Slf4j
@RestController
@RequestMapping("/server")
public class ServerApiController {

    @GetMapping("/hello")
    public String hello() {
        return "hello server";
    }

    @ApiResponse(code = 502, message = "사용자의 나이가 10살 이하일 때")
    @ApiOperation(value = "사용자의 이름과 나이를 echo하는 메소드")
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

    @GetMapping("/naver")
    public String naver() {

        URI uri = UriComponentsBuilder
                .fromUriString("https://openapi.naver.com/v1/search/local.json")
                .queryParam("query", "중국집")
                .queryParam("display", 10)
                .queryParam("start", 1)
                .queryParam("sort", "random")
                .encode(Charset.forName("UTF-8"))
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();

        RequestEntity<Void> req = RequestEntity
                .get(uri)
                .header("X-Naver-Client-Id", "PfOTrBS5jKzOGTuMk_ij")
                .header("X-Naver-Client-Secret", "WpuS4B1rFQ")
                .build();

        ResponseEntity<String> result = restTemplate.exchange(req, String.class);

        return result.getBody();
    }
}
