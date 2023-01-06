package com.example.exampletest.reflection;


import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class BookServiceTest {

    @Autowired
    BookService bookService;


    @Test
    public void di() {
        assertNotNull(bookService);
        assertNotNull(bookService.bookRepository);
    }

    @Test
    public void test() {
        Book book = new Book();
        Book book1 = new Book();

        System.out.println(book1.equals(book));
        System.out.println(book1 == book);
        System.out.println(book);
        System.out.println(book1);
    }

    @Test
    public void test1() {
        Book book = new Book("1");
        Book book1 = new Book("2");

        System.out.println(book1.equals(book));
        System.out.println(book1 == book);
        System.out.println(book);
        System.out.println(book1);
    }
}
