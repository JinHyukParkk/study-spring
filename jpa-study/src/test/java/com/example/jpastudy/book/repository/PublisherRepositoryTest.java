package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.Publisher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayName("PublisherRepository 클래스")
@SpringBootTest
class PublisherRepositoryTest {

    @Autowired
    PublisherRepository publisherRepository;

    @Test
    void createTest() {
        Publisher publisher = new Publisher();
        publisher.setId(1L);
        publisher.setName("패캠");

        publisherRepository.save(publisher);

        publisherRepository.findAll().forEach(System.out::println);

    }
}
