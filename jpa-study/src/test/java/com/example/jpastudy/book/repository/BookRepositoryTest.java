package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    void bookTest() {
        Book book = new Book();
        book.setName("jpa");
        book.setAuthor("Good Boy");

        bookRepository.save(book);

        System.out.println(bookRepository.findAll().get(0))  ;

    }

}