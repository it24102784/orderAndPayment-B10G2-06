package com.orderandpayment.orderandpayment.strategy;

import java.math.BigDecimal;

// This is the Strategy interface
public interface PaymentStrategy {
    void pay(BigDecimal amount);
}