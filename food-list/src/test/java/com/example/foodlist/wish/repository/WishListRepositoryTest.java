package com.example.foodlist.wish.repository;

import com.example.foodlist.wish.entity.WishListEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WishListRepositoryTest {

    @Autowired
    private WishListRepository wishListRepository;

    private WishListEntity create() {
        var wishList = new WishListEntity();
        wishList.setTitle("왕십리 곱창");
        wishList.setCategory("곱창");
        wishList.setAddress("왕십리");
        wishList.setRoadAddress("왕십리1");
        wishList.setHomePageLink("wang");
        wishList.setImageLink("image");
        wishList.setVisit(false);
        wishList.setVisitCount(0);
        wishList.setLastVisitDate(null);

        return wishList;
    }

    @Test
    public void saveTest() {
        var wishListEntity = create();
        var expected = wishListRepository.save(wishListEntity);

        Assertions.assertNotNull(expected);
        Assertions.assertEquals(1, expected.getIndex());

    }

    @Test
    public void updateTest() {
        var wishListEntity = create();
        var expected = wishListRepository.save(wishListEntity);

        expected.setTitle("update 왕십리");
        var saveEntity = wishListRepository.save(expected);


        Assertions.assertEquals("update 왕십리", saveEntity.getTitle());
        Assertions.assertEquals(1, wishListRepository.findAll().size());
    }

    @Test
    public void findByIdTest() {
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);

        var expected = wishListRepository.findById(1);

        Assertions.assertEquals(true, expected.isPresent());
        Assertions.assertEquals(1, expected.get().getIndex());


    }

    @Test
    public void deleteTest() {
        var wishListEntity = create();
        wishListRepository.save(wishListEntity);

        wishListRepository.deleteById(1);

        int expected = wishListRepository.findAll().size();

        Assertions.assertEquals(0, expected);
    }

    @Test
    public void listAllTest() {
        var wishListEntity1 = create();
        wishListRepository.save(wishListEntity1);
        var wishListEntity2 = create();
        wishListRepository.save(wishListEntity2);

        int expected = wishListRepository.findAll().size();

        Assertions.assertEquals(2, expected);
    }
}