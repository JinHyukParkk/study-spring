package com.example.jpastudy.book.service;

import com.example.jpastudy.book.domain.Author;
import com.example.jpastudy.book.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void putAuthor() {
        Author author = new Author();
        author.setName("hyuk");

        authorRepository.save(author);

        throw new RuntimeException("오류가 발생. 트랜잭션 ?");
    }
}
