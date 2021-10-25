package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
