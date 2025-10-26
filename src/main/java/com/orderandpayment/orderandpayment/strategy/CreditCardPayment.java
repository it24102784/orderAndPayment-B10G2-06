package com.orderandpayment.orderandpayment.strategy;

import java.math.BigDecimal;

// This is a Concrete Strategy
public class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(BigDecimal amount) {
        // In a real app, you would connect to a payment gateway API here
        System.out.println("Processing " + amount + " using Credit Card.");
    }
}