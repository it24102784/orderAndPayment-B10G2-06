package com.orderandpayment.orderandpayment.service;

import com.orderandpayment.orderandpayment.entity.Role;
import com.orderandpayment.orderandpayment.entity.User;
import com.orderandpayment.orderandpayment.repository.RoleRepository;
import com.orderandpayment.orderandpayment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User saveNewUser(User user) {
        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Find and assign the default "ROLE_USER"
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(Set.of(userRole));

        return userRepository.save(user);
    }
    // ... (inside UserService.java)

    public User updateUser(String username, User userDetails) {
        // Find the existing user by their username
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update the fields with the new information from the form
        existingUser.setFirstName(userDetails.getFirstName());
        existingUser.setLastName(userDetails.getLastName());
        existingUser.setEmail(userDetails.getEmail());

        // Note: We are NOT updating the password here.
        // That requires a separate, more complex form.

        return userRepository.save(existingUser);
    }
}
