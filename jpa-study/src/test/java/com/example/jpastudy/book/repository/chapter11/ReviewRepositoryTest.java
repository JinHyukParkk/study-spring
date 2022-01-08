package com.example.jpastudy.book.repository.chapter11;

import com.example.jpastudy.book.domain.Review;
import com.example.jpastudy.book.repository.ReviewRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @DisplayName("findall() 재선언 하기 전 - EAGER 와 LAZY 차이 확인")
    void reviewFetchTypeTest() {
        List<Review> reviews = reviewRepository.findAll();
        System.out.println("전체 가져옴");

        System.out.println(reviews.get(0).getComments());
        System.out.println("첫번째 리뷰의 Comments");

        System.out.println(reviews.get(1).getComments());
        System.out.println("두번째 리뷰의 Comments");
    }

    @Test
    @Transactional
    @DisplayName("N+1 해결 방법 - 1")
    void reviewQueryTest() {
        List<Review> reviews = reviewRepository.findAllByFetchJoin();

        reviews.forEach(System.out::println);
    }

    @Test
    @Transactional
    @DisplayName("N+1 해결 방법 - 2")
    void reviewQueryGraphTest() {
        List<Review> reviews = reviewRepository.findAllByEntityGraph();

        reviews.forEach(System.out::println);
    }

    @Test
    @Transactional
    @DisplayName("N+1 해결 방법 - 3")
    void reviewBasicQueryGraphTest() {
        List<Review> reviews = reviewRepository.findAll();

        reviews.forEach(System.out::println);
    }
}
