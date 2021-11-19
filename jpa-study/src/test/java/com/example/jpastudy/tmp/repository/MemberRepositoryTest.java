package com.example.jpastudy.tmp.repository;

import com.example.jpastudy.tmp.domain.Locker;
import com.example.jpastudy.tmp.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    LockerRepository lockerRepository;

    @Autowired
    MemberHistoryRepository memberHistoryRepository;

    @Test
    void listenerTest() {
        Member member = givenMember("hyuk", "hyuk123@gmail.com");
        memberRepository.save(member);

        Member member1 = memberRepository.findByNickName("hyuk");
        member1.setNickName("mimi");
        member1.setEmail("mimi@gmail.com");

        memberRepository.save(member1);
        System.out.println(member1);

        memberHistoryRepository.findAll().forEach(System.out::println);

    }

    @Test
    void oneToOneTest() {
        Locker locker = Locker.builder()
                .name("내꼬")
                .build();

        lockerRepository.save(locker);

        Member member = givenMember("test", "test@gmail.com", locker);

        memberRepository.save(member);

        System.out.println(">> 조회");
        memberRepository.findAll().forEach(System.out::println);
        lockerRepository.findAll().forEach(System.out::println);

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
}
