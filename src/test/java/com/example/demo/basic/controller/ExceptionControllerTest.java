package com.example.demo.basic.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = ExceptionController.class)
class ExceptionControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void test() {

    }

}