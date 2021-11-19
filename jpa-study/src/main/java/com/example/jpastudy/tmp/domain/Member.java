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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nickName;

    @NonNull
    private String email;

    private LocalDateTime nickUpdatedAt;

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;

    @Transient
    private String preNickName;
}
