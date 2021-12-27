package com.example.jpastudy.book.repository.chapter8;

import com.example.jpastudy.book.domain.Publisher;
import com.example.jpastudy.book.repository.BookRepository;
import com.example.jpastudy.book.repository.PublisherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    void bookRemoveCascadeTest() {
        bookRepository.findAll().forEach(System.out::println);
        publisherRepository.findAll().forEach(System.out::println);

        bookRepository.deleteById(1L);

        bookRepository.findAll().forEach(System.out::println);
        publisherRepository.findAll().forEach(System.out::println);

//        bookRepository.findAll().forEach(book -> System.out.println(book.getPublisher()));
    }
    /*
     publisher 도 삭제됨
     publisher 의 book 에 orphanRemoval 추가
     */

    @Test
    void publisherRemoveCascadeTest() {
        Publisher publisher = publisherRepository.findById(1L).get();
        assertThat(publisher).isNotNull();

        publisherRepository.delete(publisher);

        bookRepository.findAll().forEach(System.out::println);

    }

    @Test
    void softDelete() {
//        bookRepository.findAllByDeletedFalse().forEach(System.out::println);
//        bookRepository.findByCategoryIsNullAndDeletedFalse().forEach(System.out::println);
        bookRepository.findAll().forEach(System.out::println
        );

        /*
        deleted 조건을 넣음으로써 데이터가 삭제된 것을 표시할 수 있음
        하지만 매번 deleted=false 조건을 넣어줘야함. 누락되면 사이드 이펙트 발생
        엔티티에 옵션 @Where 사용
         */
    }


}
