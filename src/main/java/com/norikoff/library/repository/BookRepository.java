package com.norikoff.library.repository;

import com.norikoff.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findBooksByNameOrderByName(String name);

    List<Book> findBooksByAuthorOrderByAuthor(String author);

    List<Book> findBooksByPartOrderByPart(String part);

    List<Book> findBooksByAvailableOrderById(Boolean available);


}
