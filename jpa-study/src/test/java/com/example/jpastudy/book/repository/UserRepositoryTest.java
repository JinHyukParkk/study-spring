package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

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
        userRepository.deleteById(1L);
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
//    @Transactional // LAZY 일때
    void crud() {

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
}