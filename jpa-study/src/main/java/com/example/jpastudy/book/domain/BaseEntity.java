package com.example.jpastudy.book.domain;

import com.example.jpastudy.book.domain.listener.Auditable;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@MappedSuperclass // 해당 클래스를 상속받는 Entity에 포함시켜 주겠다는 클래스
@EntityListeners(value = AuditingEntityListener.class)
public class BaseEntity implements Auditable {

//    @Column(name = "crtdat", nullable = false)
//    @Column(insertable = false)  // insert 할 때 제외
    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
