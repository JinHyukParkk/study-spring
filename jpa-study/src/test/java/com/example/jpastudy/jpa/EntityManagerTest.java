package com.example.jpastudy.jpa;

import com.example.jpastudy.book.domain.User;
import com.example.jpastudy.book.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
public class EntityManagerTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void entityManagerTest() {
        System.out.println(entityManager.createQuery("select u from User u").getResultList());
    }

    @Test
    @Transactional  // 같은 transactional 내에서 ID 조회는 persistence context 에서 1차 캐시를 탐
    void cacheFindTest() {
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());
//        System.out.println(userRepository.findByEmail("hyuk@gmail.com"));
//        System.out.println(userRepository.findByEmail("hyuk@gmail.com"));
//        System.out.println(userRepository.findByEmail("hyuk@gmail.com"));

        userRepository.deleteById(1L);
    }

    @Test
    @Transactional  // 같은 transactional 내의 같은 Entity의 업데이트는 Merging 하여 하나의 update로 반영됨
    void cacheFind2Test() {
        User user = userRepository.findById(1L).get();
        user.setName("hyukkkkk");

        userRepository.save(user);

//        userRepository.flush();  // 영속성 컨텍스트에 쌓인 Entity 내용을 DB에 반영 시킴

        System.out.println("----------------------");

        user.setEmail("pjhdidisie@gmail.com");

        userRepository.save(user);
        // save는 하나의 Transaction 을 가짐. 소스보면 @Transaction 걸려져 있음.

//        userRepository.flush();

        System.out.println(">>>>1 " + userRepository.findById(1L).get());
        // 영속성 컨텐스트 캐시에 있는 값. -> 아직 DB 업데이트 되기 전

        userRepository.findAll().forEach(System.out::println);
        //

        System.out.println("- before flush ----------------------");
//        userRepository.flush();

        System.out.println(">>>>2" + userRepository.findById(1L).get());


        // persistence context 의 cache 내용이 반영되는 시점
        // 1. flush method를 명시적으로 호출하는 시점
        // 2. Transaction 이 끝나서 commit 되는 시점
        // 3. 복잡한 조회 조건의 JPQL이 실행되는 시점

    }
}
