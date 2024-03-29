package com.example.practicebatch.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private LocalDate updatedDate;

    @Builder
    public User(Long id, String username, LocalDate updatedDate) {
        this.id = id;
        this.username = username;
        this.updatedDate = updatedDate;
    }
}
