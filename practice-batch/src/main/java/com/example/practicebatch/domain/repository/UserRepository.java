package com.example.practicebatch.domain.repository;

import com.example.practicebatch.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
