package com.example.jpastudy.book.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ReviewTest {

    @Test
    @DisplayName("Review 생성 확인")
    void createReview() {
        Review review = new Review();
        review.setContent("test");

        assertThat(review.getContent()).isEqualTo("test");
    }

}
