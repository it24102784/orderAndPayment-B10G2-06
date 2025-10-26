package com.orderandpayment.orderandpayment.controller;

import com.orderandpayment.orderandpayment.entity.Order;
import com.orderandpayment.orderandpayment.entity.Payment;
import com.orderandpayment.orderandpayment.factory.PaymentStrategyFactory; // Import the factory
import com.orderandpayment.orderandpayment.service.OrderService;
import com.orderandpayment.orderandpayment.service.ShoppingCartService;
import com.orderandpayment.orderandpayment.strategy.PaymentStrategy; // Import the strategy
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
public class CheckoutController {

    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;
    private final PaymentStrategyFactory strategyFactory; // Add the factory

    // Update the constructor to inject the factory
    @Autowired
    public CheckoutController(ShoppingCartService shoppingCartService,
                              OrderService orderService,
                              PaymentStrategyFactory strategyFactory) {
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
        this.strategyFactory = strategyFactory;
    }

    @GetMapping("/checkout")
    public String checkoutForm(Model model) {
        model.addAttribute("order", new Order());
        return "checkout";
    }

    // Update the placeOrder method
    @PostMapping("/place-order")
    public String placeOrder(@ModelAttribute("order") Order order,
                             @RequestParam("paymentMethod") String paymentMethod) {

        // --- DESIGN PATTERN LOGIC ---
        try {
            // 1. Use the Factory to get the correct Strategy
            PaymentStrategy strategy = strategyFactory.getStrategy(paymentMethod);

            // 2. Execute the Strategy's "pay" algorithm
            strategy.pay(shoppingCartService.getTotal());

        } catch (IllegalArgumentException e) {
            // Handle an invalid payment method
            System.err.println(e.getMessage());
            return "redirect:/checkout?error=payment";
        }
        // --- END OF PATTERN LOGIC ---

        // Your existing logic to save the order
        order.setTotalAmount(shoppingCartService.getTotal());
        order.setStatus("Paid");
        order.setOrderDate(LocalDateTime.now());

        Payment payment = new Payment();
        payment.setAmount(shoppingCartService.getTotal());
        payment.setPaymentDate(LocalDateTime.now());
        payment.setStatus("Completed");
        payment.setPaymentMethod(paymentMethod); // Save the name of the method
        payment.setOrder(order);
        order.setPayment(payment);

        Order savedOrder = orderService.saveOrder(order);
        shoppingCartService.clearCart();

        return "redirect:/order-confirmation/" + savedOrder.getId();
    }

    @GetMapping("/order-confirmation/{orderId}")
    public String orderConfirmation(@PathVariable Long orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return "order-confirmation";
    }
}