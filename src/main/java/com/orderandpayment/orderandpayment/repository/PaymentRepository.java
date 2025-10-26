package com.orderandpayment.orderandpayment.repository;

import com.orderandpayment.orderandpayment.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}