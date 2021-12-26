package com.example.jpastudy.book.repository.chapter8;

import com.example.jpastudy.book.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void bookRemoveCascadeTest() {
        System.out.println("books : " + bookRepository.findAll());
//        System.out.println("publishers : " + publisherRepository.findAll());
    }
}
