package com.orderandpayment.orderandpayment.repository;

import com.orderandpayment.orderandpayment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}