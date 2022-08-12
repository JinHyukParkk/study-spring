package com.example.jpastudy.book.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Person person;

    @Builder
    public Pet(String name) {
        this.name = name;
    }

    public String GetOwnerName() {
        return person.getFirstName();
    }

    public String getName() {
        return name;
    }
}
