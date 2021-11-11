package com.example.jpastudy.tmp.domain;

import com.example.jpastudy.book.domain.listener.UserEntityListener;
import com.example.jpastudy.tmp.domain.listener.TestEntityListner;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value = TestEntityListner.class )
public class Person extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;
}
