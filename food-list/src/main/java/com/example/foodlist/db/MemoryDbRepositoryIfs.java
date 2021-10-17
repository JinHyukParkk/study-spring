package com.example.foodlist.db;

import java.util.List;
import java.util.Optional;

public interface MemoryDbRepositoryIfs<T> {

    Optional<T> findById(int index);
    T save(T entiry);
    void deleteById(int index);
    List<T> findAll();
}
