package com.example.qmdzy.qmdzy.service;

import com.example.qmdzy.qmdzy.model.Order;
import java.util.List;

public interface OrderService {
    Order createOrder(Long userId, List<Long> cartItemIds);
    List<Order> getUserOrders(Long userId);
    void deleteOrder(Long orderId);
}