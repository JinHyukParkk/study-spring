package com.example.jpastudy.tmp.repository;

import com.example.jpastudy.tmp.domain.MemberHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHistoryRepository extends JpaRepository<MemberHistory, Long> {
}
