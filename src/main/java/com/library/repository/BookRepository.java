package com.library.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.library.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    Book findByIsbn(String isbn);
}

