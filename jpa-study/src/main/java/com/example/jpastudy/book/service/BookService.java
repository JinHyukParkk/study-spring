package com.example.jpastudy.book.service;

import com.example.jpastudy.book.domain.Author;
import com.example.jpastudy.book.domain.Book;
import com.example.jpastudy.book.repository.AuthorRepository;
import com.example.jpastudy.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

//    @Autowired
// 최근에 필드 주입을 권장하지 않음. RequireArgsConstructor 가 생성자를 만들어줌
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final EntityManager entityManager;
    private final AuthorService authorService;

    public void put() {
        this.putBookAndAuthorRuntimeException();
    }

    // Spring Transactional 이 더 많은 기능을 제공함. 필요에 따라 다름
    @Transactional
    public void putBookAndAuthor() {
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("hyuk");

        authorRepository.save(author);
    }
    // 메소드 안 적용된 query는 @Transactional 있는 함수 끝날 때 적용됨


    public void putBookAndAuthorRuntimeException() {
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("hyuk");

        authorRepository.save(author);

        throw new RuntimeException("예외나서 DB Commit 안됨");
    }

    public void putBookAndAuthorException() throws Exception{
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("hyuk");

        authorRepository.save(author);

        throw new Exception("예외나서 DB Commit 안됨");
    }

    @Transactional(rollbackFor = Exception.class)
    public void putBookAndAuthorExceptionRollBack() throws Exception{
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        Author author = new Author();
        author.setName("hyuk");

        authorRepository.save(author);

        throw new Exception("예외나서 DB Commit 안됨");
    }

    /*
      RuntimeException 예외 발생하면 DB에 적용 안됨.
      checked Exception은 persistence context 반영 내용이 롤백되지 않고 적용됨.
      이유 ?
      TransactionAspectSupport > invokeWithinTransaction 에 completeTransactionAfterThorwing 에서 rollback 처리를 함
      단 조건이 RuntimeException 이거나 Error 타입인지 확인을 해서 rollback 처리됨
      Exception 을 포함시키기 위해서 @Transaction 속성 중 rollbackFor 사용
     */

    /*
       만약 @Transaction이 감싸진 함수가 다른 함수에 의해 호출되는 경우는
       @Transaction 의 효과가 없어짐
     */


//    @Transactional(isolation = Isolation.READ_UNCOMMITTED)  // 정합성이 깨질 수 있기 때문에 잘 사용하지 않음
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void get(Long id) {
        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        Book book = bookRepository.findById(id).get();
        book.setName("바뀜?");
        bookRepository.save(book);
    }

    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void GetRepeatable(Long id) {
        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

        entityManager.clear();

        System.out.println(">>> " + bookRepository.findById(id));
        System.out.println(">>> " + bookRepository.findAll());

//        bookRepository.update();

        entityManager.clear();

        Book book = bookRepository.findById(id).get();
        book.setName("바뀜?");
        bookRepository.save(book);
    }

    @Transactional(propagation = Propagation.REQUIRED)   // REQUIRES_NEW 는 각각의 개별 Transaction으로
    public void putBookAndAuthorPropagationRequired() {
        Book book = new Book();
        book.setName("JPA 시작하기");

        bookRepository.save(book);

        try {
            authorService.putAuthor();

        } catch (RuntimeException e) {
            System.out.println("여기서 catch : " + e.getMessage());
        }
    }

    @Transactional
    public List<Book> getAll() {
        List<Book> books = bookRepository.findAll();

        return books;
    }
}
