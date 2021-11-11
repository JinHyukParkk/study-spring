package com.example.jpastudy.tmp.repository;

import com.example.jpastudy.tmp.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByName(String name);
}
