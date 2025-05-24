package com.library.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.library.model.Book;
import com.library.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return service.listAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Long id) {
        Optional<Book> bookOpt = service.getBookById(id);
        return bookOpt.map(ResponseEntity::ok)
                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.saveBook(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        return service.getBookById(id)
            .map(existing -> {
                existing.setTitle(book.getTitle());
                existing.setAuthor(book.getAuthor());
                existing.setIsbn(book.getIsbn());
                return ResponseEntity.ok(service.saveBook(existing));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public List<Book> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String isbn) {
        if (title != null) {
            return service.searchByTitle(title);
        } else if (author != null) {
            return service.searchByAuthor(author);
        } else if (isbn != null) {
            Book book = service.searchByIsbn(isbn);
            return book != null ? List.of(book) : List.of();
        }
        return List.of();
    }
}
