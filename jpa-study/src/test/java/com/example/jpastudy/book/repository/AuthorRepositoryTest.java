package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.Author;
import com.example.jpastudy.book.domain.Book;
import com.example.jpastudy.book.domain.BookAndAuthor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookAndAuthorRepository bookAndAuthorRepository;

    @Test
    @Transactional
    void manyToManyTest() {
//        Book book1 = givenBook("Book1");
//        Book book2 = givenBook("Book1");
//        Book book3 = givenBook("DevBook1");
//        Book book4 = givenBook("DevBook1");
//
//        Author author1 = givenAuthor("hyuk");
//        Author author2 = givenAuthor("gun");

//        book1.addAuthor(author1);
//        book2.addAuthor(author2);
//        book3.addAuthor(author1, author2);
//        book4.addAuthor(author1, author2);
//
//        author1.addBook(book1, book3, book4);
//        author2.addBook(book2, book3, book4);
//
//        bookRepository.saveAll(Lists.newArrayList(book1, book2, book3, book4));
//        authorRepository.saveAll(Lists.newArrayList(author1, author2));
//
//        System.out.println("authors through book : " + bookRepository.findAll().get(1).getAuthors());
//        System.out.println("books through author : " + authorRepository.findAll().get(0).getBooks());
    }

    @Test
    @Transactional
    void changeOneToMany() {
        Book book1 = givenBook("Book1");
        Book book2 = givenBook("Book1");
        Book book3 = givenBook("DevBook1");
        Book book4 = givenBook("DevBook1");

        Author author1 = givenAuthor("hyuk");
        Author author2 = givenAuthor("gun");

        BookAndAuthor bookAndAuthor1 = givenBookAndAuthor(book1, author2);
        BookAndAuthor bookAndAuthor2 = givenBookAndAuthor(book2, author2);
        BookAndAuthor bookAndAuthor3 = givenBookAndAuthor(book3, author1);
        BookAndAuthor bookAndAuthor4 = givenBookAndAuthor(book3, author2);
        BookAndAuthor bookAndAuthor5 = givenBookAndAuthor(book4, author1);
        BookAndAuthor bookAndAuthor6 = givenBookAndAuthor(book4, author2);

        book1.addBookAndAuthor(bookAndAuthor1);
        book2.addBookAndAuthor(bookAndAuthor2);
        book3.addBookAndAuthor(bookAndAuthor3, bookAndAuthor4);
        book4.addBookAndAuthor(bookAndAuthor5, bookAndAuthor6);

        author1.addBookAndAuthor(bookAndAuthor1, bookAndAuthor3, bookAndAuthor5);
        author2.addBookAndAuthor(bookAndAuthor2, bookAndAuthor4, bookAndAuthor6);

        bookRepository.saveAll(Lists.newArrayList(book1, book2, book3, book4));
        authorRepository.saveAll(Lists.newArrayList(author1, author2));

        bookRepository.findAll().get(2).getBookAndAuthors().forEach(o -> System.out.println(o.getAuthor()));
        authorRepository.findAll().get(0).getBookAndAuthors().forEach(o -> System.out.println(o.getBook()));
    }

    private Book givenBook(String name) {
        Book book = new Book();
        book.setName(name);

        return bookRepository.save(book);
    }

    private Author givenAuthor(String name) {
        Author author = new Author();
        author.setName(name);

        return authorRepository.save(author);
    }

    private BookAndAuthor givenBookAndAuthor(Book book, Author author) {
        BookAndAuthor bookAndAuthor = new BookAndAuthor();
        bookAndAuthor.setBook(book);
        bookAndAuthor.setAuthor(author);

        return bookAndAuthorRepository.save(bookAndAuthor);
    }
}