package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
