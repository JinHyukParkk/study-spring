package com.example.jpastudy.book.repository.chapter10;

import com.example.jpastudy.book.domain.Address;
import com.example.jpastudy.book.domain.User;
import com.example.jpastudy.book.repository.UserHistoryRepository;
import com.example.jpastudy.book.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserHistoryRepository userHistoryRepository;

    @Autowired
    EntityManager entityManager;


    @Test
    void embedTest() {
        userRepository.findAll().forEach(System.out::println);

        User user = new User();
        user.setName("hyuk");
        user.setHomeAddress(new Address("성남시", "분당구", "야탑동", "39204"));
        user.setCompanyAddress(new Address("성남시", "분당구", "판교동", "12345"));

        userRepository.save(user);

        User user1 = new User();
        user1.setName("chul");
        user1.setHomeAddress(null);
        user1.setCompanyAddress(null);

        userRepository.save(user1);

        User user2 = new User();
        user2.setName("gum");
        user2.setHomeAddress(null);
        user2.setCompanyAddress(null);

        userRepository.save(user2);

        entityManager.clear();

        userRepository.findAll().forEach(System.out::println);
        userHistoryRepository.findAll().forEach(System.out::println);

        userRepository.findAllRowRecord().forEach(a -> System.out.println(a.values()));
    }
}
