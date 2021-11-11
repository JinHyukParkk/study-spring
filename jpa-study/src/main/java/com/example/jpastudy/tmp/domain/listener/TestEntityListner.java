package com.example.jpastudy.tmp.domain.listener;

import javax.persistence.*;

public class TestEntityListner {

    @PrePersist
    public void prePersist(Object o) {
        System.out.println(">> prePersist");
    }

    @PreUpdate
    public void preUpdate(Object o) {
        System.out.println(">> preUpdate");
    }

    @PreRemove
    public void preRemove(Object o) {
        System.out.println(">> preRemove");
    }

    @PostPersist
    public void postPersist(Object o) {
        System.out.println(">> postPersist");
    }

    @PostUpdate
    public void postUpdate(Object o) {
        System.out.println(">> postUpdate");
    }

    @PostRemove
    public void postRemove(Object o) {
        System.out.println(">> postRemove");
    }

    @PostLoad
    public void postLoad(Object o) {
        System.out.println(">> postLoad");
    }
}
