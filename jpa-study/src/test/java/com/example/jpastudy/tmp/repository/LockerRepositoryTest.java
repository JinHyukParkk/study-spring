package com.example.jpastudy.tmp.repository;

import com.example.jpastudy.tmp.domain.Locker;
import com.example.jpastudy.tmp.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.locks.Lock;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LockerRepositoryTest {

    @Autowired
    private LockerRepository lockerRepository;

    @Test
    void crudTest() {

    }

    private Member givenMember(String nickName, String email) {
        Member member = Member.builder()
                .nickName(nickName)
                .email(email)
                .build();

        return member;
    }

    private Member givenMember(String nickName, String email, Locker locker) {
        Member member = Member.builder()
                .nickName(nickName)
                .email(email)
                .locker(locker)
                .build();

        return member;
    }

    private Locker givenLocker(String name) {
        Locker locker = new Locker().builder()
                .name(name)
                .build();

        return locker;
    }
    private Locker givenLocker(String name, Member member) {
        Locker locker = new Locker().builder()
                .name(name)
                .member(member)
                .build();

        return locker;
    }
}