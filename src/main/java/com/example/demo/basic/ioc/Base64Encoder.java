package com.example.demo.basic.ioc;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component("base64")
public class Base64Encoder implements IEncoder{

    public String encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
}
