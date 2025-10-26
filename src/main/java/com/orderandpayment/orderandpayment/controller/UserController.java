package com.orderandpayment.orderandpayment.controller;

import com.orderandpayment.orderandpayment.entity.User;
import com.orderandpayment.orderandpayment.repository.UserRepository;
import com.orderandpayment.orderandpayment.service.UserService; // Import UserService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute; // Import ModelAttribute
import org.springframework.web.bind.annotation.PostMapping; // Import PostMapping

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService; // Autowire the UserService

    @GetMapping("/profile")
    public String viewProfile(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        model.addAttribute("user", user);
        return "profile";
    }

    // --- NEW METHOD 1: Show the Edit Form ---
    @GetMapping("/profile/edit")
    public String showEditProfileForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Pass the existing user data to the form
        model.addAttribute("user", user);
        return "profile-edit"; // Returns profile-edit.html
    }

    // --- NEW METHOD 2: Handle the Edit Form Submission ---
    @PostMapping("/profile/edit")
    public String updateProfile(@AuthenticationPrincipal UserDetails userDetails,
                                @ModelAttribute("user") User user) {
        String username = userDetails.getUsername();
        userService.updateUser(username, user);

        return "redirect:/profile?success"; // Redirect back to the profile page with a success message
    }
}