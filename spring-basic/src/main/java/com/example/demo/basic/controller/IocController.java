package com.example.demo.basic.controller;

import com.example.demo.basic.ioc.ApplicationContextProvider;
import com.example.demo.basic.ioc.Base64Encoder;
import com.example.demo.basic.ioc.Encoder;
import com.example.demo.basic.ioc.UrlEncoder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ioc")
public class IocController {

    @GetMapping("/test")
    public String test() {
        ApplicationContext context = ApplicationContextProvider.getContext();

        // @Component 설정된 Bean 수동으로 가져옴
        Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);

        // @Bean 으로 설정된 Bean 수동으로 가져옴
        Encoder encoder = context.getBean("base64Encode", Encoder.class);
        String url = "www.naver.com/books/it?page=106&size=20&name=spring-boot";

        String result = encoder.encode(url);
        System.out.println(result);

        encoder.setiEncoder(urlEncoder);
        result = encoder.encode(url);
        System.out.println(result);

        return result;

    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
