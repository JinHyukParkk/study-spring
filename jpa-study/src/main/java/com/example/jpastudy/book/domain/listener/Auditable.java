package com.example.jpastudy.book.domain.listener;

import java.time.LocalDateTime;

public interface Auditable {
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();

    void setCreatedAt(LocalDateTime createAt);
    void setUpdatedAt(LocalDateTime updatedAt);
}
