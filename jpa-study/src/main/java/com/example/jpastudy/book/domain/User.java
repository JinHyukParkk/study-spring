package com.example.jpastudy.book.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

//@Getter @Setter
//@ToString // java 객체에서 toString 오버라이딩 하는 것을 권고함.
//@EqualsAndHashCode

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@Entity
@Table(name="user_tbl", indexes = { @Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull  // RequriredArgsConstructor 어노테이션을 써도 필수 값으로 있어야함
    private String name;

    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)  // enum 타입에 붙임. 안붙이게 되면 넘버 값으로 들어가게 됨
    private Gender gender;

//    @Column(name = "crtdat", nullable = false)
//    @Column(nullable = false)   // update 할 때 제외
    @Column(updatable = false)
    private LocalDateTime createdAt;

//    @Column(insertable = false)  // insert 할 때 제외
    private LocalDateTime updatedAt;

    @Transient  // DB에 반영하지 않음
    private String testData;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;

    @PrePersist
    public void prePersist() {
        System.out.println(">>> prePersist");
    }

    @PostPersist
    public void postPersist() {
        System.out.println(">>> postPersist");
    }

//    @PrePersist     // insert 메소드가 실행되기 전 실행
//    @PreUpdate      // update 메소드가 실행되기 전 실행
//    @PreRemove      // remove 메소드가 실행되기 전 실행
//    @PostPersist    // insert 메소드가 실행되기 후 실행
//    @PostUpdate     // update 메소드가 실행되기 후 실행
//    @PostRemove     // remove 메소드가 실행되기 후 실행
//    @PostLoad       // select 메소드가 일어난 직 후

}
