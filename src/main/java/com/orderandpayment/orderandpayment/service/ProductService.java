package com.orderandpayment.orderandpayment.service;

import com.orderandpayment.orderandpayment.entity.Product; // <-- FIX: Capital P
import com.orderandpayment.orderandpayment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // You need to import Optional

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findAllProducts() { // <-- FIX: Capital P
        return productRepository.findAll();
    }
}