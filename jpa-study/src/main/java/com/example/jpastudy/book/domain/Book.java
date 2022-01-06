package com.example.jpastudy.book.domain;

import com.example.jpastudy.book.domain.converter.BookStatusConverter;
import com.example.jpastudy.book.repository.dto.BookStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@DynamicUpdate
//@DynamicUpdate
//@EntityListeners(value = MyEntityListner.class)
@EntityListeners(value = AuditingEntityListener.class)
@Where(clause = "deleted = false")
public class Book extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String category;

    @OneToOne(mappedBy = "book")
    @ToString.Exclude
    private BookReviewInfo bookReviewInfo;

    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

//    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ManyToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Publisher publisher;

    private boolean deleted;

//    @Convert(converter = BookStatusConverter.class)  // Converter 에 autoApply=true가 되어있으면 생략 가능
    private BookStatus status;

//    @ManyToMany
    @OneToMany
    @JoinColumn(name = "book_id")
    @ToString.Exclude
    private List<BookAndAuthor> bookAndAuthors = new ArrayList<>();

    public void addBookAndAuthor(BookAndAuthor... bookAndAuthor) {

        Collections.addAll(this.bookAndAuthors, bookAndAuthor);
    }

//    public void addAuthor(Author... author) {
//        Collections.addAll(this.authors, author);
//    }

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
