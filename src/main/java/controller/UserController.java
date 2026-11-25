package com.example.twitterapp.controller;

import com.example.twitterapp.model.User;
import com.example.twitterapp.service.UserService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model, User userDto) {
        model.addAttribute("user", userDto);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, User userDto) {
        model.addAttribute("user", userDto);
        return "register";
    }

    @PostMapping("/register")
    public String registerSava(@ModelAttribute("user") User userDto, Model model) {
        String username = userDto.getUsername() == null ? "" : userDto.getUsername().trim();
        String password = userDto.getPassword();

        if (username.length() < 3 || username.length() > 40) {
            model.addAttribute("registrationError", "Choose a username between 3 and 40 characters.");
            return "register";
        }
        if (password == null || password.length() < 8) {
            model.addAttribute("registrationError", "Your password must be at least 8 characters.");
            return "register";
        }

        userDto.setUsername(username);
        User user = userService.findByUsername(username);
        if (user != null) {
            model.addAttribute("Userexist", true);
            return "register";
        }

        try {
            userService.save(userDto);
        } catch (DataIntegrityViolationException exception) {
            // A second registration can win the unique-name race after the lookup.
            model.addAttribute("Userexist", true);
            return "register";
        }
        return "redirect:/register?success";
    }
}
