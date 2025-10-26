package com.orderandpayment.orderandpayment.entity;

import java.math.BigDecimal;

public class CartItem {
    private Product Product;
    private int quantity;

    public CartItem(Product product) {
        this.Product = product;
        this.quantity = 1;
    }

    public Product getProduct() {
        return Product;
    }

    public void setProduct(Product product) {
        Product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
// Add Getters and Setters
}