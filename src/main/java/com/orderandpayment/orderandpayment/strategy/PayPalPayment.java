package com.orderandpayment.orderandpayment.strategy;

import java.math.BigDecimal;

// This is another Concrete Strategy
public class PayPalPayment implements PaymentStrategy {
    @Override
    public void pay(BigDecimal amount) {
        // In a real app, you would connect to the PayPal API here
        System.out.println("Processing " + amount + " using PayPal.");
    }
}