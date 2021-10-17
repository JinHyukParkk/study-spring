package com.example.foodlist.wish.repository;

import com.example.foodlist.db.MemoryDbRepositroyAbstract;
import com.example.foodlist.wish.entity.WishListEntity;
import org.springframework.stereotype.Repository;

@Repository
public class WishListRepository extends MemoryDbRepositroyAbstract<WishListEntity> {
}
