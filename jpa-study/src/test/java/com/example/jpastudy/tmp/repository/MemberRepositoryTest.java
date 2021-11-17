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
    MemberHistoryRepository memberHistoryRepository;

    @Test
    void listenerTest() {
        Member member = givenMember("test", "test123@gmail.com");

        Member member1 = memberRepository.findByNickName("hyuk");
        member1.setNickName("mimi");
        member1.setEmail("mimi@gmail.com");

        memberRepository.save(member1);
        System.out.println(member1);

        memberHistoryRepository.findAll().forEach(System.out::println);
    }

    @Test
    void oneToOneTest() {
        Member member = givenMember("test", "test@gmail.com");

        Locker locker = Locker.builder()
                .name("내꼬")
                .member(member)
                .build();

        memberRepository.findAll().forEach(System.out::println);

    }

    private Member givenMember(String nickName, String email) {
        Member member = Member.builder()
                .nickName(nickName)
                .email(email)
                .build();

        return memberRepository.save(member);
    }

}
