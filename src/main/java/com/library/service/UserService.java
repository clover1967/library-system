package com.library.service;

import org.springframework.stereotype.Service;
import com.library.model.User;
import com.library.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepo;
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    public User saveUser(User user) {
        return userRepo.save(user);
    }
}
