package com.example.jpastudy.tmp.repository;

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
        Member member = Member.builder()
                .nickName("hyuk")
                .email("hyuk@gmail.com")
                .build();

        memberRepository.save(member);
        System.out.println(member);

        Member member1 = memberRepository.findByNickName("hyuk");
        member1.setNickName("mimi");
        member1.setEmail("mimi@gmail.com");

        memberRepository.save(member1);
        System.out.println(member1);

        memberHistoryRepository.findAll().forEach(System.out::println);
    }

    @Test
    public void insertTest() {
        Member member = Member.builder()
                .nickName("test123")
                .email("test123@gmail.com")
                .build();

        memberRepository.save(member);
        System.out.println(member);
    }

}
