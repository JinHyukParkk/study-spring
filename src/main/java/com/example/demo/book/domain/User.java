package com.example.demo.book.domain;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

//@Getter @Setter
//@ToString // java 객체에서 toString 오버라이딩 하는 것을 권고함.
//@NoArgsConstructor
//@AllArgsConstructor
//@RequiredArgsConstructor
//@EqualsAndHashCode
@Data
@Builder
public class User {
    @NotNull  // RequriredArgsConstructor 어노테이션을 써도 필수 값으로 있어야함
    private String name;

    @NotNull
    private String email;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
