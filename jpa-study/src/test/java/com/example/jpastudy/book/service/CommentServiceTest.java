package com.example.jpastudy.book.service;

import com.example.jpastudy.book.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void commentTest() {
        commentService.init();

        commentService.updateSomething();
    }

    @Test
    void insertTest() {
        commentService.insertSomething();
    }
}
