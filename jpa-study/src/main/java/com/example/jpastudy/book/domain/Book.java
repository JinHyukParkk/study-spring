package com.example.jpastudy.book.domain;

import com.example.jpastudy.book.domain.listener.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
//@EntityListeners(value = MyEntityListner.class)
//@EntityListeners(value = AuditingEntityListener.class)
public class Book extends BaseEntity implements Auditable {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String category;

    private Long author_id;

    private Long publisher_id;







//    @PrePersist
//    public void prePersist() {
//        this.createdAt = LocalDateTime.now();
//        this.updatedAt = LocalDateTime.now();
//        System.out.println(">>> prePersist");
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        this.updatedAt = LocalDateTime.now();
//    }
}
