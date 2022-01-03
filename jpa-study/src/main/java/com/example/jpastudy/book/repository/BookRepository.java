package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.Book;
import com.example.jpastudy.book.repository.dto.BookNameAndCategory;
import com.example.jpastudy.book.repository.dto.NameAndDeleted;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.time.LocalDateTime;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

//    @Modifying
//    @Query(value = "update book set category='none', nativeQuery = true")
//    void update();

    List<Book> findAllByDeletedFalse();

    List<Book> findByCategoryIsNullAndDeletedFalse();

    List<Book> findByCategoryIsNullAndNameEqualsAndCreatedAtGreaterThanEqualAndUpdatedAtGreaterThanEqual(String name, LocalDateTime createdAt, LocalDateTime updatedAt);

//    @Query(value = "select b from Book b " +
//            "where name = ?1 and createdAt >= ?2 and updatedAt >= ?3 and category is null")
//    List<Book> findByNameRecently(String name, LocalDateTime createdAt, LocalDateTime updateAt);

    @Query(value = "select b from Book b " +
            "where name = :name and createdAt >= :createdAt and updatedAt >= :updatedAt and category is null")
    List<Book> findByNameRecently(
            @Param("name") String name,
            @Param("createdAt") LocalDateTime createdAt,
            @Param("updatedAt") LocalDateTime updatedAt);

    @Query(value = "select b.name as name, b.category as category from Book b")
    List<Tuple> findBookNameAndCategory();

    @Query(value = "select b.name as name, b.deleted as deleted from Book b")
    List<NameAndDeleted> findNameAndDeleted();

 }
