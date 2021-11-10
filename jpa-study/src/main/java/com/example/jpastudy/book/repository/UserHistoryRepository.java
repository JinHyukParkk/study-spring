package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}
