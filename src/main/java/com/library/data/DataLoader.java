package com.library.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.library.model.Book;
import com.library.model.User;
import com.library.repository.BookRepository;
import com.library.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {
    private final UserRepository userRepo;
    private final BookRepository bookRepo;
    private final PasswordEncoder encoder;
    public DataLoader(UserRepository ur, BookRepository br, PasswordEncoder pe) {
        this.userRepo = ur; this.bookRepo = br; this.encoder = pe;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.count() == 0) {
            User admin = new User("admin", encoder.encode("adminpass"), "ROLE_ADMIN");
            User user = new User("user", encoder.encode("userpass"), "ROLE_USER");
            userRepo.save(admin);
            userRepo.save(user);
        }
        if (bookRepo.count() == 0) {
            bookRepo.save(new Book("Don Quixote", "Miguel de Cervantes", "978-0000000000"));
            bookRepo.save(new Book("To Kill a Mockingbird", "Harper Lee", "978-1111111111"));
            bookRepo.save(new Book("Treasure Island", "Robert Louis Stevenson", "978-2222222222"));
        }
    }
}
