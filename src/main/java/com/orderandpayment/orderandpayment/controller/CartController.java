package com.orderandpayment.orderandpayment.controller;

import com.orderandpayment.orderandpayment.entity.Product;
import com.orderandpayment.orderandpayment.service.ProductService;
import com.orderandpayment.orderandpayment.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class CartController {

    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;

    @Autowired
    public CartController(ShoppingCartService shoppingCartService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
    }

    @GetMapping("/cart")
    public String viewCart(Model model) {
        model.addAttribute("cartItems", shoppingCartService.getItems().values());
        model.addAttribute("total", shoppingCartService.getTotal());
        return "cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam("productId") Long productId) {
        Optional<Product> p = productService.findProductById(productId);
        p.ifPresent(shoppingCartService::addProduct);
        return "redirect:/products";
    }

    // --- NEW METHOD ---
    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam("productId") Long productId) {
        Optional<Product> p = productService.findProductById(productId);
        p.ifPresent(shoppingCartService::removeProduct);
        return "redirect:/cart";
    }
}