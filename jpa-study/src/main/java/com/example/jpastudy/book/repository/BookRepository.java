package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
