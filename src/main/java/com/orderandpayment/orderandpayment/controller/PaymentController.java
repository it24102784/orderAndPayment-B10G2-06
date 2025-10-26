package com.orderandpayment.orderandpayment.controller;

import com.orderandpayment.orderandpayment.entity.Order;
import com.orderandpayment.orderandpayment.entity.Payment;
import com.orderandpayment.orderandpayment.service.OrderService;
import com.orderandpayment.orderandpayment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
public class PaymentController {

    private final OrderService orderService;
    private final PaymentService paymentService;

    @Autowired
    public PaymentController(OrderService orderService, PaymentService paymentService) {
        this.orderService = orderService;
        this.paymentService = paymentService;
    }

    @PostMapping("/admin/payments/add")
    public String addPayment(@RequestParam("orderId") Long orderId,
                             @RequestParam("amount") BigDecimal amount,
                             @RequestParam("paymentMethod") String paymentMethod) {

        Optional<Order> optionalOrder = orderService.findOrderById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            Payment newPayment = new Payment();
            newPayment.setAmount(amount);
            newPayment.setPaymentMethod(paymentMethod);
            newPayment.setStatus("Completed");
            newPayment.setPaymentDate(LocalDateTime.now());
            newPayment.setOrder(order);

            paymentService.savePayment(newPayment);
        }

        return "redirect:/admin/orders/details/" + orderId;
    }
}