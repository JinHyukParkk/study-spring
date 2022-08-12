package com.example.jpastudy.book.service;

import com.example.jpastudy.book.domain.Person;
import com.example.jpastudy.book.domain.Pet;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
public class PersonService {

    private final EntityManager entityManager;

    public void addPet(Long personId, String petName) {
        Person person = entityManager.find(Person.class, personId);
        person.add(Pet.builder()
                        .name(petName)
                        .build());

        entityManager.persist(person);
        entityManager.flush();
    }
}
