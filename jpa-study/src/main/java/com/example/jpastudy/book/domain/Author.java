package com.example.jpastudy.book.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Author extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String country;

    private Long bookId;


}
