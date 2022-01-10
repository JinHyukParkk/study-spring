package com.example.jpastudy.book.repository.chapter11;

import com.example.jpastudy.book.domain.Comment;
import com.example.jpastudy.book.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void commentTest() {
        Comment comment = new Comment();
        comment.setComment("별로");
        comment.setCommentedAt(LocalDateTime.now());

        commentRepository.save(comment);

        System.out.println(comment);
    }
}
