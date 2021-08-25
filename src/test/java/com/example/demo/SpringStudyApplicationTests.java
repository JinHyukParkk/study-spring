package com.example.demo;

import com.example.demo.models.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringStudyApplicationTests {

    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("test");

        // Text Json -> Object
        // Object -> Text Json

        // controller request json(text) -> Response Object
        // Response Object -> json(text)

        var objectMapper = new ObjectMapper();

        // object -> text
        var user = User.builder()
                            .name("hyuk")
                            .age(29)
                            .build();

        // object mapper 는 get method 활용함
        var text = objectMapper.writeValueAsString(user);
        System.out.println(text);


        // text -> object
        var objectUser = objectMapper.readValue(text, User.class);
        System.out.println(objectUser);

    }

}
