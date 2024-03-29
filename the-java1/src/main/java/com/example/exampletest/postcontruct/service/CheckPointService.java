package com.example.exampletest.postcontruct.service;

import com.example.exampletest.postcontruct.domain.CheckPoint;
import com.example.exampletest.postcontruct.repository.CheckPointRepository;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//        throw new RuntimeException();
    }
}
