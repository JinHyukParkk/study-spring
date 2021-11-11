package com.example.jpastudy.tmp.repository;

import com.example.jpastudy.tmp.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByNickName(String nickName);
}
