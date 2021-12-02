package com.example.jpastudy.book.service;

import com.example.jpastudy.book.domain.Author;
import com.example.jpastudy.book.domain.Book;
import com.example.jpastudy.book.repository.AuthorRepository;
import com.example.jpastudy.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

//    @Autowired
// 최근에 필드 주입을 권장하지 않음. RequireArgsConstructor 가 생성자를 만들어줌
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    // Spring Transactional 이 더 많은 기능을 제공함. 필요에 따라 다름
    @Transactional
    public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("hyuk");

        authorRepository.save(author);
    }
    // 메소드 안 적용된 query는 @Transactional 있는 함수 끝날 때 적용됨

}
