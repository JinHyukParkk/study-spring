package com.example.jpastudy.book.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    @DisplayName("Book 전체 출력")
    void converterErrorTest() {
        bookService.getAll().forEach(System.out::println);
    }

}
