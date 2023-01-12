package com.example.exampletest.postcontruct.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.exampletest.postcontruct.domain.CheckPoint;

public interface CheckPointRepository extends JpaRepository<CheckPoint, Long> {

}
//@Repository
//@RequiredArgsConstructor
//public class CheckPointRepository {
//
//    private final EntityManager entityManager;
//
//    public Long save(CheckPoint checkPoint) {
//        entityManager.persist(checkPoint);
//        return checkPoint.getId();
//    }
//}
