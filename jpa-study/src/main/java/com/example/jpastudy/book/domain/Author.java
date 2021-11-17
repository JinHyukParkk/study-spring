package com.example.jpastudy.book.domain;

import javax.persistence.*;

@Entity
public class Author extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    private Long bookId;


}
