package com.example.exampletest.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CheckPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    public CheckPoint(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "CheckPoint{" +
               "id=" + id +
               ", email='" + value + '\'' +
               '}';
    }
}
