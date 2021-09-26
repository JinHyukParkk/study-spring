package com.example.demo.basic.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DeleteApiController.class)
class DeleteApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAopGet() throws Exception
    {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("account", "123");

        mockMvc.perform(
                        delete("/api/delete/hyuk")
                                .params(params))
                .andDo(print())
                .andExpect(status().isOk());
    }

}