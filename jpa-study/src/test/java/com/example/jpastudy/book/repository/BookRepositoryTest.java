package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookReviewInfoRepository bookReviewInfoRepository;

    @BeforeEach
    void beforeGivenData() {

    }

    @Test
    void bookReviewTest() {
        Book book = givenBook(givenPublisher());
        givenBookReviewInfo(book);

        bookReviewInfoRepository.findAll().forEach(System.out::println);

        Book result = bookReviewInfoRepository.findById(1L)
                .orElseThrow(RuntimeException::new)
                .getBook();

        System.out.println(">>>" + result);

        bookRepository.findAll().forEach(System.out::println);

        BookReviewInfo result1 = bookRepository.findById(2L)
                .orElseThrow(RuntimeException::new)
                .getBookReviewInfo();

        System.out.println(">>>" + result1);
    }

    @Test
    void bookRelationTest() {
        givenReview(givenUser(), givenBook(givenPublisher()));

        User user = userRepository.findByEmail("hyuk@gmail.com");

        System.out.println("Review : " + user.getReviews());

        System.out.println("Book : " + user.getReviews().get(0).getBook());

        System.out.println("Publisher : " + user.getReviews().get(0).getBook().getPublisher());

    }

    private Book givenBook(Publisher publisher) {
        Book book = new Book();
        book.setName("JPA 초보");
        book.setPublisher(publisher);

        return bookRepository.save(book);
    }

    private void givenBookReviewInfo(Book book) {
        BookReviewInfo bookReviewInfo = new BookReviewInfo();
        bookReviewInfo.setBook(book);
        bookReviewInfo.setAverageReviewScore(4.5f);
        bookReviewInfo.setReviewCount(2);

        bookReviewInfoRepository.save(bookReviewInfo);
    }

    private void givenReview(User user, Book book) {
        Review review = new Review();
        review.setTitle("내 인생 책");
        review.setContent("읽다가 깨달음");
        review.setScore(5.0f);
        review.setUser(user);
        review.setBook(book);

        reviewRepository.save(review);
    }

    private User givenUser() {
        return userRepository.getByEmail("hyuk@gmail.com");
    }

    private Publisher givenPublisher() {
        Publisher publisher = new Publisher();
        publisher.setName("패캠");

        return publisherRepository.save(publisher);
    }
}