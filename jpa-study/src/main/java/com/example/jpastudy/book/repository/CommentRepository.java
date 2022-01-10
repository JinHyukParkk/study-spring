package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
