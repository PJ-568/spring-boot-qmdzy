package com.example.qmdzy.qmdzy.controller;

import com.example.qmdzy.qmdzy.model.CartItem;
import com.example.qmdzy.qmdzy.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String viewCart(Authentication authentication, Model model) {
        Long userId = getCurrentUserId(authentication);
        List<CartItem> cartItems = cartService.getCartItems(userId);
        model.addAttribute("cartItems", cartItems);
        return "cart";
    }

    @PostMapping("/add")
    public String addToCart(Authentication authentication,
            @RequestParam Long productId,
            @RequestParam(defaultValue = "1") int quantity) {
        Long userId = getCurrentUserId(authentication);
        cartService.addToCart(userId, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/update")
    public String updateQuantity(Authentication authentication,
            @RequestParam Long productId,
            @RequestParam int quantity) {
        Long userId = getCurrentUserId(authentication);
        cartService.updateQuantity(userId, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String removeFromCart(Authentication authentication,
            @RequestParam Long productId) {
        Long userId = getCurrentUserId(authentication);
        cartService.removeFromCart(userId, productId);
        return "redirect:/cart";
    }

    @PostMapping("/clear")
    public String clearCart(Authentication authentication) {
        Long userId = getCurrentUserId(authentication);
        cartService.clearCart(userId);
        return "redirect:/cart";
    }

    private Long getCurrentUserId(Authentication authentication) {
        // 从认证信息中获取当前用户ID
        return ((com.example.qmdzy.qmdzy.model.User) authentication.getPrincipal()).getId();
    }
}