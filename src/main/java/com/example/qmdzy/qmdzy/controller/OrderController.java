package com.example.qmdzy.qmdzy.controller;

import com.example.qmdzy.qmdzy.model.Order;
import com.example.qmdzy.qmdzy.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getOrders(Authentication authentication, Model model) {
        Long userId = getCurrentUserId(authentication);
        List<Order> orders = orderService.getUserOrders(userId);
        model.addAttribute("orders", orders);
        return "order";
    }

    @PostMapping("/create")
    public String createOrder(Authentication authentication, 
                            @RequestParam List<Long> cartItemIds) {
        Long userId = getCurrentUserId(authentication);
        orderService.createOrder(userId, cartItemIds);
        return "redirect:/order";
    }

    @PostMapping("/delete")
    public String deleteOrder(@RequestParam Long orderId) {
        orderService.deleteOrder(orderId);
        return "redirect:/order";
    }

    private Long getCurrentUserId(Authentication authentication) {
        return ((com.example.qmdzy.qmdzy.model.User) authentication.getPrincipal()).getId();
    }
}