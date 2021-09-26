package com.example.demo.client.service;

import com.example.demo.basic.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    public String hello() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/server/hello")
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();

        // 1. object 로 받기
//        String result = restTemplate.getForObject(uri, String.class);

        // 2. entity 로 받기
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }

    public UserResponse user() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/server/user")
                .queryParam("name", "chul")
                .queryParam("age", 29)
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();

        // 1. object 로 받기
//        UserResponse result = restTemplate.getForObject(uri, UserResponse.class);

        // 2. entity 로 받기
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);

        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }
}
