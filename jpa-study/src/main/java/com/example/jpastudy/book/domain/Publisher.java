package com.example.jpastudy.book.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Publisher extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany
    @JoinColumn(name = "publisher_id")              // 중간 테이블인 publisher_books 를 생성하지 않게 JoinColumn 선언
    @ToString.Exclude
    private List<Book> books = new ArrayList<>();   // NullPointException 방지 차 빈 ArrayList 선언

    public void addBook(Book book) {
        this.books.add(book);
    }


}
