package com.example.jpastudy.book.repository.chapter11;

import com.example.jpastudy.book.domain.Comment;
import com.example.jpastudy.book.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void commentTest() {
        Comment comment = new Comment();
        comment.setComment("별로");
//        comment.setCommentedAt(LocalDateTime.now());

        commentRepository.saveAndFlush(comment);

        entityManager.clear();

        System.out.println(comment);

        commentRepository.findAll().forEach(System.out::println);

        System.out.println(commentRepository.findById(4L));
    }
}
/**
 * 처음 영속성 컨텍스트에서는 날짜가 null 로 찍힘
 * 하지만 영속성 컨텍스트를 비워주고 다시 DB에서 읽어올 경우 날짜가 찍혀있음
 * Entity 객체에서 DynamicInsert 로 default 된 값을 DB에 넣어주고, 영속성 컨텍스트에서는 반영되어 있지 않가
 */
