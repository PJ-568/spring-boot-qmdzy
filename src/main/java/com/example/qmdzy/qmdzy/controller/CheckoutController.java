package com.example.qmdzy.qmdzy.controller;

import com.example.qmdzy.qmdzy.model.CartItem;
import com.example.qmdzy.qmdzy.service.CartService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;
import java.util.List;

@Controller
public class CheckoutController {

    private final CartService cartService;

    public CheckoutController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/checkout")
    public String checkout(Authentication authentication,
                         @RequestParam List<Long> cartItemIds,
                         Model model) {
        Long userId = getCurrentUserId(authentication);
        
        List<CartItem> selectedCartItems = cartItemIds.stream()
            .map(id -> cartService.getCartItemById(id))
            .collect(Collectors.toList());
            
        // 验证购物车项属于当前用户
        selectedCartItems.forEach(item -> {
            if(!item.getUser().getId().equals(userId)) {
                throw new RuntimeException("非法操作：购物车项不属于当前用户");
            }
        });
        
        model.addAttribute("selectedCartItems", selectedCartItems);
        return "checkout";
    }

    private Long getCurrentUserId(Authentication authentication) {
        return ((com.example.qmdzy.qmdzy.model.User) authentication.getPrincipal()).getId();
    }
}