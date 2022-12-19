package com.example.exampletest;

import com.example.exampletest.domain.CheckPoint;
import com.example.exampletest.repository.CheckPointRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Transactional
@Component
public class InitReadyEvent {

    @Autowired
    CheckPointRepository checkPointRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void initSentry() {
        System.out.println("#### ApplicationReadyEvent ###");
        checkPointRepository.save(new CheckPoint("d"));
//        throw new RuntimeException();
    }
}
