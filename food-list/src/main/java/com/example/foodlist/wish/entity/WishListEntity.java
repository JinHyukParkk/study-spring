package com.example.foodlist.wish.entity;

import com.example.foodlist.db.MemoryDbEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
public class WishListEntity extends MemoryDbEntity {

    private String title;                   // 음식명
    private String category;                // 카테고리
    private String address;                 // 주소
    private String roadAddress;             // 도로명 주소
    private String homePageLink;            // 홈페이지 주소
    private String imageLink;               // 이미지 주소
    private boolean isVisit;                // 방문 여부
    private int visitCount;                 // 방문수
    private LocalDateTime lastVisitDate;    // 마지막 방문일

}
