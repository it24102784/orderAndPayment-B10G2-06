package com.orderandpayment.orderandpayment.controller;

import com.orderandpayment.orderandpayment.entity.Product;
import com.orderandpayment.orderandpayment.service.ProductService;
import com.orderandpayment.orderandpayment.service.ShoppingCartService; // Import this
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final ShoppingCartService shoppingCartService; // Add this

    @Autowired
    public ProductController(ProductService productService, ShoppingCartService shoppingCartService) { // Update constructor
        this.productService = productService;
        this.shoppingCartService = shoppingCartService; // Add this
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Product> productList = productService.findAllProducts();
        model.addAttribute("products", productList);
        // Add the number of items in the cart to the model
        model.addAttribute("cartItemCount", shoppingCartService.getItems().size());
        return "products";
    }
}