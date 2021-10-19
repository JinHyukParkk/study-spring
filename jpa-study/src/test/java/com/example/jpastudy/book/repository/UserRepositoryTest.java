package com.example.jpastudy.book.repository;

import com.example.jpastudy.book.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
//    @Transactional // LAZY 일때
    void crud() {
        // Read
//        List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));
//        List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));

        // Create
//        User user1 = new User("jack", "jack@naver.com");
//        User user2 = new User("steve", "steve@naver.com");
//        userRepository.saveAll(Lists.newArrayList(user1, user2));
//        userRepository.save(user1);

        // getOne은 기본적으로 Entity 에서 LAZY한 FETCH을 지원함
//        User user = userRepository.getOne(1L);
//        System.out.println(user);

        // findById는 Optional로 래핑됨, EAGER FETCH 사용
//        Optional<User> user = userRepository.findById(10L);
//        User user = userRepository.findById(10L).orElse(null);
//        System.out.println(user);

        // flush
//        userRepository.save(new User("new hyuk", "pjh0819@naver.com"));
//        userRepository.flush();
        // save and flush
//        userRepository.saveAndFlush(new User("new hyuk", "pjh0819@naver.com"));
//        userRepository.findAll().forEach(System.out::println);

        // count
//        long count = userRepository.count();
//        System.out.println(count);
//
        // exist
//        boolean exists = userRepository.existsById(1L);
//        System.out.println(exists);

        // delete
//        userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));
//        userRepository.deleteById(1L);

        // Page
//        Page<User> users = userRepository.findAll(PageRequest.of(1, 3));
//        System.out.println("page : " + users);
//        System.out.println("totalElement : " + users.getTotalElements());
//        System.out.println("totalPages : " + users.getTotalPages());
//        System.out.println("numberOfElements : " + users.getNumberOfElements());
//        System.out.println("sort : " + users.getSort());
//        System.out.println("size : " + users.getSize());
//
//        users.getContent().forEach(System.out::println) ;
//        System.out.println(users);

        // Example
//        Example<User> example = Example.of(new User("hyuk", "hyuk@gmail.com"));
//        userRepository.findAll(example).forEach(System.out::println);

//        ExampleMatcher matcher = ExampleMatcher.matching()
//                .withIgnorePaths("name")
//                .withMatcher("email", endsWith());
          // "ma" 부분은 withIgnorePaths("name") 설정으로 무시됨
//        Example<User> example = Example.of(new User("ma", "gmail.com"), matcher);
//        userRepository.findAll(example).forEach(System.out::println);

        User user = new User();
        user.setEmail("naver.com");
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("email", contains());
        Example<User> example = Example.of(user, matcher);
        userRepository.findAll(example).forEach(System.out::println);
    }
}