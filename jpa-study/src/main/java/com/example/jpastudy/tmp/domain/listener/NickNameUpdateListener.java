package com.example.jpastudy.tmp.domain.listener;

import com.example.jpastudy.book.support.BeanUtils;
import com.example.jpastudy.tmp.domain.Member;
import com.example.jpastudy.tmp.domain.MemberHistory;
import com.example.jpastudy.tmp.repository.MemberHistoryRepository;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class NickNameUpdateListener {

    @PostLoad
    public void postLoad(Member member) {
        member.setPreNickName(member.getNickName());
    }

    @PrePersist
    public void prePersist(Member member) {
        if (member.getPreNickName() == null) {
            updateNickName(member);
        }
    }

    @PreUpdate
    public void preUpdate(Member member) {
        if (!member.getPreNickName().equals(member.getNickName())) {
            updateNickName(member);
        }
    }

    void updateNickName(Member member) {
        member.setNickUpdatedAt(LocalDateTime.now());

        MemberHistoryRepository memberHistoryRepository = BeanUtils.getBean(MemberHistoryRepository.class);

        MemberHistory memberHistory = MemberHistory.builder()
                .member(member)
                .beforeNickName(member.getPreNickName())
                .afterNickName(member.getNickName())
                .build();

        memberHistoryRepository.save(memberHistory);
    }
}
