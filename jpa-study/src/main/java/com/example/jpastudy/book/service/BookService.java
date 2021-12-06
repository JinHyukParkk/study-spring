package com.example.jpastudy.book.service;

import com.example.jpastudy.book.domain.Author;
import com.example.jpastudy.book.domain.Book;
import com.example.jpastudy.book.repository.AuthorRepository;
import com.example.jpastudy.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

//    @Autowired
// 최근에 필드 주입을 권장하지 않음. RequireArgsConstructor 가 생성자를 만들어줌
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

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
}
