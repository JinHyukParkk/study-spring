package com.example.jpastudy.book.service;

import com.example.jpastudy.book.domain.User;
import com.example.jpastudy.book.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void put() {
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@gmail.com");

        //        userRepository.save(user);
        entityManager.persist(user);
        entityManager.detach(user); // 준영속성 상태, 아래 변경 내용이 적용 안됨

        user.setName("newUserAfter");
        entityManager.merge(user);  // 준영속성 상태를 영속성 상태로 만듬

        entityManager.flush();      // 영속성 DB 반영
        entityManager.clear();      // 영속성 상태 삭제 - flush 전에 하면 변경 내용 삭제됨

        User user1 = userRepository.findById(1L).get();
        entityManager.remove(user1);

        user1.setName("hyuk123123123");
        entityManager.merge(user1); // 삭제된 instance 는 merge 불가
    }
}
