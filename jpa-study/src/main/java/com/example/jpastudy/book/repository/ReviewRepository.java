package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
