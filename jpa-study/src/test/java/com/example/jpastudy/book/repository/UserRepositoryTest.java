package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.Gender;
import com.example.jpastudy.book.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void findTest() {
        // Read
        List<User> sortList = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
        List<User> idList = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));

        System.out.println(sortList);
        System.out.println(idList);
    }

    @Test
    void create() {
        // Create
        User user1 = new User("jack", "jack@naver.com");
        User user2 = new User("steve", "steve@naver.com");
        userRepository.saveAll(Lists.newArrayList(user1, user2));
        userRepository.save(user1);
    }

    @Test
    @Transactional
    void getOneTest() {
        // getOne은 기본적으로 Entity 에서 LAZY한 FETCH을 지원함
        User user = userRepository.getOne(1L);
        System.out.println(user);
    }

    @Test
    void optionalTest() {
        // findById는 Optional로 래핑됨, EAGER FETCH 사용
        Optional<User> user = userRepository.findById(10L);
        User user1 = userRepository.findById(10L).orElse(null);
        System.out.println(user);
        System.out.println(user1);
    }

    @Test
    void flush() {
        // flush
        userRepository.save(new User("new hyuk", "pjh0819@naver.com"));
        userRepository.flush();
    }

    @Test
    void flushAndSave() {
        // save and flush
        userRepository.saveAndFlush(new User("new hyuk", "pjh0819@naver.com"));
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void countTest() {
        // count
        long count = userRepository.count();
        System.out.println(count);
    }

    @Test
    void existTest() {
        // exist
        boolean exists = userRepository.existsById(1L);
        System.out.println(exists);
    }

    @Test
    void deleteTest() {
        // delete
        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
//        userRepository.deleteById(1L);
    }

    @Test
    void PageTest() {
        // Page
        Page<User> users = userRepository.findAll(PageRequest.of(1, 3));
        System.out.println("page : " + users);
        System.out.println("totalElement : " + users.getTotalElements());
        System.out.println("totalPages : " + users.getTotalPages());
        System.out.println("numberOfElements : " + users.getNumberOfElements());
        System.out.println("sort : " + users.getSort());
        System.out.println("size : " + users.getSize());

        users.getContent().forEach(System.out::println) ;
        System.out.println(users);
    }

    @Test
    void exampleTest() {
        // Example
        Example<User> example = Example.of(new User("hyuk", "hyuk@gmail.com"));
        userRepository.findAll(example).forEach(System.out::println);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("name")
                .withMatcher("email", endsWith());
//         "ma" 부분은 withIgnorePaths("name") 설정으로 무시됨
        Example<User> example1 = Example.of(new User("ma", "gmail.com"), matcher);
        userRepository.findAll(example1).forEach(System.out::println);

        User user = new User();
        user.setEmail("naver.com");
        ExampleMatcher matcher1 = ExampleMatcher.matching().withMatcher("email", contains());
        Example<User> example2 = Example.of(user, matcher1);
        userRepository.findAll(example).forEach(System.out::println);
    }

    @Test
    void update() {
        userRepository.save(new User("woong", "woong@naver.com"));

        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("woong-updated@naver.com");

        userRepository.save(user);

    }

    @Test
    void select() {
//        System.out.println(userRepository.findByName("gun"));
        System.out.println("findByName :" + userRepository.findByName("hyuk"));

        System.out.println("findByEmail :" + userRepository.findByEmail("hyuk@gmail.com"));
        System.out.println("getByEmail :" + userRepository.getByEmail("hyuk@gmail.com"));
        System.out.println("readByEmail :" + userRepository.readByEmail("hyuk@gmail.com"));
        System.out.println("queryByEmail :" + userRepository.queryByEmail("hyuk@gmail.com"));
        System.out.println("searchByEmail :" + userRepository.searchByEmail("hyuk@gmail.com"));
        System.out.println("streamByEmail :" + userRepository.streamByEmail("hyuk@gmail.com"));
        System.out.println("findUserByEmail :" + userRepository.findUserByEmail("hyuk@gmail.com"));
        System.out.println("findSomethingByEmail :" + userRepository.findSomethingByEmail("hyuk@gmail.com"));
        System.out.println("findTop1ByName :" + userRepository.findTop1ByName("hyuk"));
        System.out.println("findFirst1ByName :" + userRepository.findFirst1ByName("gun"));
        System.out.println("findFirst2ByName :" + userRepository.findFirst2ByName("gun"));
        System.out.println("findLast1ByName :" + userRepository.findLast1ByName("gun"));
    }

    @Test
    void whereInSelect() {
        System.out.println("findByEmailAndName :" + userRepository.findByEmailAndName("gun@gmail.com","gun"));
        System.out.println("findByEmailOrName :" + userRepository.findByEmailOrName("gun@gmail.com","hyuk"));
        System.out.println("findByCreatedAtAfter :" + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByIdAfter :" + userRepository.findByIdAfter(1L));
        System.out.println("findByCreatedAtGreaterThan :" + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterThanEqual :" + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtBetween :" + userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now()));
        System.out.println("findByIdBetween :" + userRepository.findByIdBetween(1L, 4L));
        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual :" + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 4L));

        System.out.println("findByIdIsNotNull :" + userRepository.findByIdIsNotNull());
//        System.out.println("findByAddressNotEmpty :" + userRepository.findByAddressNotEmpty());

        System.out.println("findByNameIn :" + userRepository.findByNameIn(Lists.newArrayList("hyuk", "gun")));

    }

    @Test
    void whereLikeSelect() {
        System.out.println("findByNameStartingWith :" + userRepository.findByNameStartingWith("hy"));
        System.out.println("findByNameEndingWith :" + userRepository.findByNameEndingWith("uk"));
        System.out.println("findByNameContains :" + userRepository.findByNameContains("hyuk"));
        System.out.println("findByNameLike :" + userRepository.findByNameLike ("%hyuk%"));
    }

    @Test
    void pagingAndSortingTest() {
        System.out.println("findTop1ByName :" + userRepository.findTop1ByName("gun"));
        System.out.println("findLast1ByName :" + userRepository.findLast1ByName("gun"));
        System.out.println("findTop1ByNameOrderByIdDesc :" + userRepository.findTop1ByNameOrderByIdDesc("gun"));
        System.out.println("findFirstByNameOrderByIdDescEmailAsc :" + userRepository.findFirstByNameOrderByIdDescEmailAsc("gun"));

        // 너무 나열하면 코드 가독성이 떨어짐
        System.out.println("findFirstByNameWithSortParams :" + userRepository.findFirstByName("gun", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));

        System.out.println("findByNameWithPaging :" + userRepository.findByName("gun", PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))));
    }

    @Test
    void insertAndUpdateTest() {
        User user = new User();
        user.setName("hyuk123");
        user.setEmail("hyuk123@gmail.com");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        System.out.println(user2.getEmail());
        user2.setName("hyuk123");
        user2.setEmail("hyuk2eo@gmail.com");

        userRepository.save(user2);
    }

    @Test
    void enumTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user);

        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRowRecord().get("gender"));
    }
}