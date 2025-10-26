package com.orderandpayment.orderandpayment.service;

import com.orderandpayment.orderandpayment.entity.CartItem;
import com.orderandpayment.orderandpayment.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class ShoppingCartService {

    private Map<Long, CartItem> items = new HashMap<>();

    // Updated to use 'p' as the parameter name
    public void addProduct(Product p) {
        CartItem item = items.get(p.getId());
        if (item == null) {
            items.put(p.getId(), new CartItem(p));
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
    }
    // ... (inside ShoppingCartService.java)
    public void clearCart() {
        items.clear();
    }

    // --- NEW METHOD ---
    public void removeProduct(Product product) {
        CartItem item = items.get(product.getId());
        if (item != null) {
            if (item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
            } else {
                items.remove(product.getId());
            }
        }
    }

    public Map<Long, CartItem> getItems() {
        return items;
    }

    public BigDecimal getTotal() {
        return items.values().stream()
                .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}