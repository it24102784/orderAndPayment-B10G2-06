package com.orderandpayment.orderandpayment.repository;

import com.orderandpayment.orderandpayment.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // Spring Boot automatically creates methods like findAll(), findById(), save(), etc.
    // You don't need to write any code here for basic operations.
}