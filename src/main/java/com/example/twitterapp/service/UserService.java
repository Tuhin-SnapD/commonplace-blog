package com.example.twitterapp.service;

import com.example.twitterapp.model.User;
import com.example.twitterapp.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository users;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

    public User findByUsername(String username) { return users.findByUsername(username); }
    public User save(User user) {
        user.setUsername(user.getUsername().trim());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return users.save(user);
    }
}
