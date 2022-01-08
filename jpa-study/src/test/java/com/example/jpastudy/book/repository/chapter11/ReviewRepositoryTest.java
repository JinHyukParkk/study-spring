package com.example.jpastudy.book.repository.chapter11;

import com.example.jpastudy.book.domain.Review;
import com.example.jpastudy.book.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;


    @Test
    void reviewTest() {
        List<Review> reviews = reviewRepository.findAll();

        System.out.println(reviews);
    }

}
