package com.example.jpastudy.book.domain;

import com.example.jpastudy.book.domain.listener.MyEntityListner;
import com.example.jpastudy.book.domain.listener.UserEntityListener;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Builder;
import lombok.NonNull;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Getter @Setter
//@ToString // java 객체에서 toString 오버라이딩 하는 것을 권고함.
//@EqualsAndHashCode

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@EntityListeners(value = {MyEntityListner.class, UserEntityListener.class } )
//@EntityListeners(value = UserEntityListener.class )
@Table(indexes = { @Index(columnList = "name")}, uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull  // RequriredArgsConstructor 어노테이션을 써도 필수 값으로 있어야함
    private String name;

    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)  // enum 타입에 붙임. 안붙이게 되면 넘버 값으로 들어가게 됨
    private Gender gender;

    // 클래스를 따로 만들어서 쓰던지, AttributeOverride를 쓰던지는 협의 후 결정
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "city", column = @Column(name = "home_city")),
            @AttributeOverride(name = "district", column = @Column(name = "home_district")),
            @AttributeOverride(name = "detail", column = @Column(name = "home_detail")),
            @AttributeOverride(name = "zipCode", column = @Column(name = "home_zipCode"))
    })
    private Address homeAddress;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "city", column = @Column(name = "company_city")),
        @AttributeOverride(name = "district", column = @Column(name = "company_district")),
        @AttributeOverride(name = "detail", column = @Column(name = "company_detail")),
        @AttributeOverride(name = "zipCode", column = @Column(name = "company_zipCode"))
    })
    private Address companyAddress;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @ToString.Exclude
    @Builder.Default
    private List<UserHistory> userHistoryList = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();

    @Transient  // DB에 반영하지 않음
    private String testData;

//    @OneToMany(fetch = FetchType.EAGER)
//    private List<Address> address;

    // BaseEntity 상속으로 기능 대체
//    @Column(name = "crtdat", nullable = false)
//    @Column(insertable = false)  // insert 할 때 제외
//    @CreatedDate
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    private LocalDateTime updatedAt;


    // MyEntityListener.Class 로 기능 대체
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
//
//    @PostPersist
//    public void postPersist() {
//        System.out.println(">>> postPersist");
//    }

//    @PrePersist     // insert 메소드가 실행되기 전 실행
//    @PreUpdate      // update 메소드가 실행되기 전 실행
//    @PreRemove      // remove 메소드가 실행되기 전 실행
//    @PostPersist    // insert 메소드가 실행되기 후 실행
//    @PostUpdate     // update 메소드가 실행되기 후 실행
//    @PostRemove     // remove 메소드가 실행되기 후 실행
//    @PostLoad       // select 메소드가 일어난 직 후

}
