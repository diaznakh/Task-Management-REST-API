package com.zaid.taskmanager.service;

import com.zaid.taskmanager.model.Role;
import com.zaid.taskmanager.model.User;
import com.zaid.taskmanager.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository users;

    public UserService(UserRepository users) {
        this.users = users;
    }

    @Transactional
    public User register(String email, String password) {
        Optional<User> existing = users.findByEmail(email);
        if (existing.isPresent()) {
            throw new IllegalStateException("Email already registered");
        }
        User u = new User(email, password, Role.USER);
        return users.save(u);
    }

    public Optional<User> findById(Long id) { return users.findById(id); }
    public Optional<User> findByEmail(String email) { return users.findByEmail(email); }
}
