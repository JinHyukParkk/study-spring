package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.Book;
import com.example.jpastudy.book.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookReviewInfoRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Test
    void crudTest() {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(givenBook());
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> " + bookReviewInfoRepository.findAll());
    }

    private Book givenBook() {
        Book book = new Book();
        book.setName("JPA 초보");
//        book.setAuthorId(1L);
//        book.setPublisherId(1L);

        return bookRepository.save(book);

    }
}