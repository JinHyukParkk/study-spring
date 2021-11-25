package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.Book;
import com.example.jpastudy.book.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;


    @Test
    void crudTest() {

    }

}