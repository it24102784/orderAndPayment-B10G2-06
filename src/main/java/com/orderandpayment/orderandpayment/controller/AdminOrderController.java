package com.orderandpayment.orderandpayment.controller;

import com.orderandpayment.orderandpayment.entity.Order;
import com.orderandpayment.orderandpayment.entity.Payment; // Make sure this is imported
import com.orderandpayment.orderandpayment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminOrderController {

    private final OrderService orderService;

    @Autowired
    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/orders")
    public String viewAllOrders(Model model) {
        List<Order> orderList = orderService.findAllOrders();
        model.addAttribute("orders", orderList);
        return "admin-dashboard";
    }

    @GetMapping("/admin/orders/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Order> optionalOrder = orderService.findOrderById(id);
        if (optionalOrder.isPresent()) {
            model.addAttribute("order", optionalOrder.get());
            return "edit-order";
        }
        return "redirect:/admin/orders";
    }

    @PostMapping("/admin/orders/update/{id}")
    public String updateOrder(@PathVariable Long id, @ModelAttribute("order") Order orderDetails) {
        Optional<Order> optionalOrder = orderService.findOrderById(id);
        if (optionalOrder.isPresent()) {
            Order existingOrder = optionalOrder.get();
            existingOrder.setStatus(orderDetails.getStatus());
            orderService.saveOrder(existingOrder);
        }
        return "redirect:/admin/orders";
    }

    // --- THIS IS THE FIXED METHOD ---
    @GetMapping("/admin/orders/details/{id}")
    public String viewOrderDetails(@PathVariable Long id, Model model) {
        Optional<Order> optionalOrder = orderService.findOrderById(id); // Now uses the service
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            model.addAttribute("order", order);
            model.addAttribute("newPayment", new Payment());
            return "order-details";
        }
        return "redirect:/admin/orders";
    }

    @PostMapping("/admin/orders/delete/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrderById(id);
        return "redirect:/admin/orders";
    }
}