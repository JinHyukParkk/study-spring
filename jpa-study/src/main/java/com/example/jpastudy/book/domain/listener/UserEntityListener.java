package com.example.jpastudy.book.domain.listener;

import com.example.jpastudy.book.domain.User;
import com.example.jpastudy.book.domain.UserHistory;
import com.example.jpastudy.book.repository.UserHistoryRepository;
import com.example.jpastudy.book.support.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;


public class UserEntityListener {

    @PostPersist
    @PostUpdate
    public void prePersistAndUpdate(Object o) {
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user = (User) o;

        UserHistory userHistory = new UserHistory();
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user);
        userHistory.setHomeAddress(user.getHomeAddress());
        userHistory.setCompanyAddress(user.getCompanyAddress());

        userHistoryRepository.save(userHistory);
    }
}
