package com.orderandpayment.orderandpayment.controller;

import com.orderandpayment.orderandpayment.entity.User;
import com.orderandpayment.orderandpayment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Show the custom login page
    @GetMapping("/login")
    public String login() {
        return "login"; // Returns login.html
    }

    // Show the registration page
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register"; // Returns register.html
    }

    // Handle the registration form POST
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.saveNewUser(user);
        return "redirect:/login?register_success";
    }
}