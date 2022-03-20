package com.example.jpastudy.book.repository.chapter9;

import com.example.jpastudy.book.domain.Book;
import com.example.jpastudy.book.repository.BookRepository;
import com.example.jpastudy.book.repository.PublisherRepository;
import com.example.jpastudy.book.repository.dto.BookStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    void quertTest() {
        String bookName = "Spring 기초";
        bookRepository.findAll().forEach(System.out::println);

        System.out.println("findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual : " +
                bookRepository.findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(
                        bookName,
                        LocalDateTime.now().minusDays(1L),
                        LocalDateTime.now().minusDays(1L)
                ));

        System.out.println("findByNameRecently : " +
                bookRepository.findByNameRecently(bookName,
                        LocalDateTime.now().minusDays(1L),
                        LocalDateTime.now().minusDays(1L)
                ));
    }
    @Test
    @DisplayName("DTO 객체 사용해서 데이터 가져오기")
    void testNameAndCategory() {
        bookRepository.findBookNameAndCategory().forEach(tuple -> {
            System.out.println(tuple.get(0) + ":" + tuple.get(1));
        });

        bookRepository.findNameAndDeleted().forEach( b-> {
            System.out.println(b.getName() + " " + b.isDeleted());
        });

    }

    @Test
    @DisplayName("Native Query 사용하기")
    void testNativeQuery() {
        bookRepository.findAll().forEach(System.out::println);

        bookRepository.findAllCustom().forEach(System.out::println);
        // deleted 된거 무시됨.
    }

    @Test
    @DisplayName("Update 시 일괄로 업데이트 됨")
    void testUpdateNativeQuery() {
        System.out.println("affected rows: " + bookRepository.updateCategories());

        bookRepository.findAllCustom().forEach(System.out::println);

        bookRepository.showTables().forEach(System.out::println);
    }

    @Test
    @DisplayName("Book Converter 테스트")
    void testConverter() {
        bookRepository.findAll().forEach(System.out::println);

        Book book = new Book();
        book.setName("Spring 고도화");
        book.setStatus(new BookStatus(200));
        bookRepository.save(book);

        bookRepository.findAll().forEach(System.out::println);

        System.out.println(bookRepository.findRowRecord().values());
    }
}
