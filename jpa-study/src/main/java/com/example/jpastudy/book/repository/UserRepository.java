package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByName(String name);

//    Set<User> findByUserByNameIs(String name);
//    Set<User> findByUserByName(String name);
//    Set<User> findByUserByNameEquals(String name);

    User findByEmail(String email);

    User getByEmail(String email);

    User readByEmail(String email);

    User queryByEmail(String email);

    User searchByEmail(String email);

    User streamByEmail(String email);

    User findUserByEmail(String email);

    User findSomethingByEmail(String email);

    List<User> findFirst1ByName(String name);

    List<User> findFirst2ByName(String name);

    List<User> findTop1ByName(String name);

    // 없는 쿼리는 무시되기 때문에 'Last1' 이 무시되고 findByName 과 동일해짐
    List<User> findLast1ByName(String name);

    List<User> findByEmailAndName(String email, String name);

    List<User> findByEmailOrName(String email, String name);

    List<User> findByCreatedAtAfter(LocalDateTime localDateTime);

    List<User> findByIdAfter(Long id);

    List<User> findByCreatedAtGreaterThan(LocalDateTime localDateTime);

    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime localDateTime);

    List<User> findByCreatedAtBetween(LocalDateTime beforeDay, LocalDateTime afterDay);

    List<User> findByIdBetween(Long id1, Long id2);

    // Camel case 준수하지 않으면 JPA 전체 Error 남
    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long Id1, Long id2);

    List<User> findByIdIsNotNull();

//    List<User> findByAddressNotEmpty();

    List<User> findByNameIn(List<String> names);

    List<User> findByNameStartingWith(String name);

    List<User> findByNameEndingWith(String name);

    List<User> findByNameContains(String name);

    List<User> findByNameLike(String name);

    List<User> findTop1ByNameOrderByIdDesc(String name);

    List<User> findFirstByNameOrderByIdDescEmailAsc(String name);

    List<User> findFirstByName(String name, Sort sort);

    List<User> findByName(String name, Pageable pageable);

    @Query(value = "select * from user limit 1;", nativeQuery = true)
    Map<String, Object> findRowRecord();

    @Query(value = "select * from user", nativeQuery = true)
    List<Map<String, Object>> findAllRowRecord();

}
