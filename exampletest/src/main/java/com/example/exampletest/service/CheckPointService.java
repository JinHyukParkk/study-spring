package com.example.exampletest.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.exampletest.domain.CheckPoint;
import com.example.exampletest.repository.CheckPointRepository;

@Transactional
@Service
public class CheckPointService {

    @Autowired
    CheckPointRepository checkPointRepository;

    @PostConstruct
    public void execute() {
        System.out.println("### PostConstruct ###");
        checkPointRepository.save(new CheckPoint("a"));
        checkPointRepository.save(new CheckPoint("b"));
        checkPointRepository.save(new CheckPoint("c"));
        throw new RuntimeException();
    }
}
