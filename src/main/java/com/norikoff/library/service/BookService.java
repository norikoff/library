package com.norikoff.library.service;

import com.norikoff.library.domain.Book;
import com.norikoff.library.exeption.AppExeption;
import com.norikoff.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> list() {
        return bookRepository.findAll();
    }

    public List<Book> listByCriteria(Book book) {
        if (book.getAuthor() != null)
            return bookRepository.findBooksByAuthorOrderByAuthor(book.getAuthor());
        if (book.getName() != null)
            return bookRepository.findBooksByNameOrderByName(book.getName());
        if (book.getAvailable() != null)
            return bookRepository.findBooksByAvailableOrderById(book.getAvailable());
        if (book.getPart() != null)
            return bookRepository.findBooksByPartOrderByPart(book.getPart());
        return null;
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public boolean remove(Long id) {
        bookRepository.deleteById(id);
        return !bookRepository.findById(id).isPresent();
    }

    public Book update(Book book) throws Exception {
        final Book someBook = Optional.ofNullable(book.getId()).map(id -> bookRepository.findById(id).map(findedBook -> {
            if (book.getName() != null)
                findedBook.setName(book.getName());
            if (book.getAuthor() != null)
                findedBook.setAuthor(book.getAuthor());
            if (book.getPart() != null)
                findedBook.setPart(book.getPart());
            if (book.getAvailable() != null)
                findedBook.setAvailable(book.getAvailable());
            return findedBook;
        }).orElse(null)).orElseThrow(() -> AppExeption.create("Can't update"));
        return bookRepository.save(someBook);
    }


}
