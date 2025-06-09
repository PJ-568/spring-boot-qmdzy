package com.example.qmdzy.qmdzy.controller;

import com.example.qmdzy.qmdzy.model.CartItem;
import com.example.qmdzy.qmdzy.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class CheckoutController {

    private final CartService cartService;

    public CheckoutController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/checkout")
    public String checkout(Authentication authentication, Model model) {
        Long userId = getCurrentUserId(authentication);
        List<CartItem> cartItems = cartService.getCartItems(userId);
        BigDecimal totalAmount = cartItems.stream()
                .map(CartItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalAmount", totalAmount);
        return "checkout";
    }

    private Long getCurrentUserId(Authentication authentication) {
        return ((com.example.qmdzy.qmdzy.model.User) authentication.getPrincipal()).getId();
    }
}