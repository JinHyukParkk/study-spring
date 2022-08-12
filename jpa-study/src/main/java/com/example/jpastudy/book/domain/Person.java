package com.example.jpastudy.book.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    @OneToMany(mappedBy = "person")
    public Set<Pet> pets = new HashSet<>();

    public void add(Pet pet) {
        pet.setPerson(this);
        getPets().add(pet);
    }
}
