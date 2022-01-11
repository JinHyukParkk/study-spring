package com.example.jpastudy.book.service;

import com.example.jpastudy.book.domain.Comment;
import com.example.jpastudy.book.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Transactional
    public void init() {
        for (int i = 0; i < 10; i++) {
            Comment comment = new Comment();
            comment.setComment("최고" + i);

            commentRepository.save(comment);
        }
    }

    @Transactional(readOnly = true)
    public void updateSomething() {
        List<Comment> comments = commentRepository.findAll();

        for (Comment comment : comments) {
            comment.setComment("별로");

//            commentRepository.save(comment);
        }
    }

    /**
     * update 시 dirty 체크를 하게 되어 save를 안해줘도 DB에 반영됨 => 배치 같이 데이터 많은 프로그램에서는 오래걸림
     * (readOnly = true)을 넣어줌으로써 dirty 체크를 안하도록 함.
     */

    @Transactional(readOnly = true)
    public void insertSomething() {
//        Comment comment = new Comment();
        Comment comment = commentRepository.findById(1L).get();
        comment.setComment("새로운거");

    }
}
