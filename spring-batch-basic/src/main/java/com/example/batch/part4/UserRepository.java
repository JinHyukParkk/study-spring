package com.example.batch.part4;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {

    Collection<User> findAllByUpdatedDate(LocalDate updatedDate);
}
