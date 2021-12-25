package com.example.jpastudy.book.repository.chapter8;

import com.example.jpastudy.book.domain.Book;
import com.example.jpastudy.book.domain.Publisher;
import com.example.jpastudy.book.repository.AuthorRepository;
import com.example.jpastudy.book.repository.BookRepository;
import com.example.jpastudy.book.repository.PublisherRepository;
import com.example.jpastudy.book.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BookRepositoryTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private PublisherRepository publisherRepository;


    @Test
    void bookRemoveCascadeTest() {

    }


}
