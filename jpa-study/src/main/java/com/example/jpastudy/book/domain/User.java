package com.example.jpastudy.book.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

//@Getter @Setter
//@ToString // java 객체에서 toString 오버라이딩 하는 것을 권고함.
//@EqualsAndHashCode

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull  // RequriredArgsConstructor 어노테이션을 써도 필수 값으로 있어야함
    private String name;

    @NonNull
    private String email;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
