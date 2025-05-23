package com.library.service;

import java.util.List;
import java.util.Optional;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.library.model.Book;
import com.library.repository.BookRepository;

@Service
public class BookService {
    private static final Logger logger = LoggerFactory.getLogger(BookService.class);
    private final BookRepository bookRepo;

    public BookService(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Cacheable("books")
    public List<Book> listAllBooks() {
        logger.info("Fetching all books from DB");
        return bookRepo.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepo.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }

    @Cacheable("searchByTitle")
    public List<Book> searchByTitle(String title) {
        logger.info("Searching books by title: {}", title);
        return bookRepo.findByTitleContainingIgnoreCase(title);
    }

    @Cacheable("searchByAuthor")
    public List<Book> searchByAuthor(String author) {
        logger.info("Searching books by author: {}", author);
        return bookRepo.findByAuthorContainingIgnoreCase(author);
    }

    @Cacheable("searchByIsbn")
    public Book searchByIsbn(String isbn) {
        logger.info("Searching book by ISBN: {}", isbn);
        return bookRepo.findByIsbn(isbn);
    }
}
