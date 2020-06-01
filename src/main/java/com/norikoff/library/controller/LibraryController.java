package com.norikoff.library.controller;

import com.norikoff.library.domain.Book;
import com.norikoff.library.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library")
@AllArgsConstructor
public class LibraryController {

    private final BookService bookService;

    @GetMapping(produces = "application/json")
    public List<Book> getBooks() {
        return bookService.list();
    }

    @GetMapping(value = "/criteria", produces = "application/json")
    public List<Book> getBooksByCriteria(@RequestParam String name, @RequestParam String author,
                                         @RequestParam String part, @RequestParam Boolean available) {
        Book build = Book.builder().name(name).author(author).part(part).available(available).build();
        return bookService.listByCriteria(build);
    }

    @PostMapping(produces = "application/json")
    public Book saveBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public Book getBook(@PathVariable Long id) {
        return bookService.find(id);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public boolean delBook(@PathVariable Long id) {
        return bookService.remove(id);
    }

    @PutMapping(produces = "application/json")
    public Book updateBook(@RequestBody Book book) throws Exception {
        return bookService.update(book);
    }
}
