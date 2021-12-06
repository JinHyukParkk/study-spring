package com.example.jpastudy.book.service;

import com.example.jpastudy.book.repository.AuthorRepository;
import com.example.jpastudy.book.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void transactionTest() {
        bookService.putBookAndAuthor();

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("authors : " + authorRepository.findAll());
    }

    @Test
    @DisplayName("Transaction 여부에 따른 save 하는 로직에서 RuntimeException 날 때 확인")
    void transactionRuntimeExceptionTest() {
        try {
            bookService.putBookAndAuthorRuntimeException();
        } catch (RuntimeException e) {
            System.out.println(">>>" + e.getMessage());
        }
        // DB에 반영 안됨
    }

    @Test
    @DisplayName("Transaction 여부에 따른 save 하는 로직에서 checked Exception 날 때 확인")
    void transactionExceptionTest() {
        try {
            bookService.putBookAndAuthorRuntimeException();
        } catch (Exception e) {
            System.out.println(">>>" + e.getMessage());
        }
        // DB에 반영됨
    }

    @Test
    @DisplayName("Transaction 여부에 따른 save 하는 로직에서 checked Exception 날 때 Rollback 확인")
    void transactionExceptionRollbackTest() {
        try {
            bookService.putBookAndAuthorExceptionRollBack();
        } catch (Exception e) {
            System.out.println(">>>" + e.getMessage());
        }
        // DB에 반영됨
    }

    @Test
    @DisplayName("Transaction 감싸진 메소드 호출")
    public void putBookAndAuthorExternanlMethod() {
        try {
            bookService.put();
        } catch (RuntimeException e) {
            System.out.println(">>>" + e.getMessage());
        }
    }

    @Test
    @DisplayName("Transaction Isolaction ")
    public void isolationTest() {

    }
}