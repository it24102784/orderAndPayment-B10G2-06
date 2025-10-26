package com.orderandpayment.orderandpayment.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customer_name;
    private LocalDateTime order_date;
    private String status;
    private BigDecimal total_amount;

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customer_name;
    }

    public void setCustomerName(String customerName) {
        this.customer_name = customerName;
    }

    public LocalDateTime getOrderDate() {
        return order_date;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.order_date = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalAmount() {
        return total_amount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.total_amount = totalAmount;
    }
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Payment payment;

    // --- Existing Getters and Setters are here ---


    // --- ADD GETTER AND SETTER FOR THE NEW FIELD ---
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}