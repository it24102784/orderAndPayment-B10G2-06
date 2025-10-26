package com.orderandpayment.orderandpayment.repository;

import com.orderandpayment.orderandpayment.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // This method is crucial for Spring Security
    Optional<User> findByUsername(String username);
}
