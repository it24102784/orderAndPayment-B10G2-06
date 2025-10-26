package com.orderandpayment.orderandpayment.factory;

import com.orderandpayment.orderandpayment.strategy.CreditCardPayment;
import com.orderandpayment.orderandpayment.strategy.PayPalPayment;
import com.orderandpayment.orderandpayment.strategy.PaymentStrategy;
import org.springframework.stereotype.Service;

// This is the Factory class
@Service // We make it a Spring service so it can be injected (Singleton)
public class PaymentStrategyFactory {

    public PaymentStrategy getStrategy(String paymentMethod) {
        if ("Credit Card".equalsIgnoreCase(paymentMethod)) {
            return new CreditCardPayment();
        } else if ("PayPal".equalsIgnoreCase(paymentMethod)) {
            return new PayPalPayment();
        }
        // You can add more strategies here (e.g., "Cash on Delivery")
        throw new IllegalArgumentException("Unknown payment method: " + paymentMethod);
    }
}