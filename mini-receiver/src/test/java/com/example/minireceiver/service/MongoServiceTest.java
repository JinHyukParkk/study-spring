package com.example.minireceiver.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MongoServiceTest {

    @Autowired
    MongoService mongoService;

    @Test
    void test() {
        mongoService.create();
    }
}
