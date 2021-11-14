package com.example.jpastudy.book.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Publisher extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;


}
