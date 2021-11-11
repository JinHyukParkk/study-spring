package com.example.jpastudy.tmp.domain;

import com.example.jpastudy.tmp.domain.listener.NickNameUpdateListener;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@EntityListeners(value = NickNameUpdateListener.class)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String nickName;

    @NonNull
    private String email;

    private LocalDateTime nickUpdatedAt;

    @Transient
    private String preNickName;

}
