package com.example.jpastudy.book.repository.chapter7;

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
        Book book = new Book();
        book.setName("JPA 강의");

        bookRepository.save(book);

        bookService.get(1L);

        System.out.println(">>> " + bookRepository.findAll());
    }
    @Test
    @DisplayName("Transaction Isolaction ")
    public void isolationTestRepeatable() {
        Book book = new Book();
        book.setName("JPA 강의");

        bookRepository.save(book);

        bookService.get(1L);

        System.out.println(">>> " + bookRepository.findAll());
    }

    @Test
    @DisplayName("Transaction Propagation Required")
    public void testPropagationRequired() {
        try {
            bookService.putBookAndAuthorPropagationRequired();
        } catch (Exception e) {
            System.out.println(">>>" + e.getMessage());
        }
    }

    //    @Transactional
    @Test
    @DisplayName("Cascade 테스트")
    void bookCascadeTest() {
        Book book = new Book();
        book.setName("Jpa 초급");

        Publisher publisher = new Publisher();
        publisher.setName("패캠");

        book.setPublisher(publisher);
        bookRepository.save(book);
        // Book 의 publisher 에 cascade persist 옵션을 넣어줌으로써 pubilsher에도 데이터가 들어감.

        System.out.println("books : " + bookRepository.findAll());
        System.out.println("publisher : " + publisherRepository.findAll());

        Book book1 = bookRepository.findById(1L).get();
        book1.getPublisher().setName("슬캠");

        bookRepository.save(book1);
        // Book 의 publisher 에 cascade merge 옵션이 있어야 update 되는 영속성이 반영이 됨

        System.out.println("publishers : " + publisherRepository.findAll());
    }
    /*
    Access to DialectResolutionInfo cannot be null when "hibernate.dialect' not set ..
    => mysql 서버 문제.

    fail to lazily initialize a collection of role : .... - no Session ..
    => 1. @Transaction 어노테이션을 붙임
    => 2. @ToString.Exclude - books의 순환 참조 끊어주기 위해 붙임
     */
}
