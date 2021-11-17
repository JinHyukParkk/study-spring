package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.Book;
import com.example.jpastudy.book.domain.BookReviewInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @Test
    void bookReviewTest() {
        Book book = givenBook();
        givenBookReviewInfo(book);

        Book result = bookReviewInfoRepository.findById(1L)
                .orElseThrow(RuntimeException::new)
                .getBook();

        System.out.println(">>>" + result);

        BookReviewInfo result1 = bookRepository.findById(1L)
                .orElseThrow(RuntimeException::new)
                .getBookReviewInfo();

        System.out.println(">>>" + result1);
    }

    private Book givenBook() {
        Book book = new Book();
        book.setName("JPA 초보");
        book.setAuthorId(1L);
        book.setPublisherId(1L);

        return bookRepository.save(book);
    }

    private void givenBookReviewInfo(Book book) {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(book);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);

        System.out.println(">>> " + bookReviewInfoRepository.findAll().get(0));
    }

}