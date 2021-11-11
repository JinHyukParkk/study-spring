package com.example.jpastudy.tmp.repository;

import com.example.jpastudy.book.domain.User;
import com.example.jpastudy.tmp.domain.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PersonRepositoryTest {
    @Autowired
    PersonRepository personRepository;

    @Test
    void listenerTest() {
        Person person = new Person();
        person.setName("hyuk");
        person.setEmail("hyuk@naver.com");

        personRepository.save(person);

        Person person2 = personRepository.findByName("hyuk");
        person2.setName("hyuk123");

        personRepository.save(person2);

        personRepository.delete(person2);
    }


}
