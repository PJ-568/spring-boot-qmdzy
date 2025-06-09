package com.example.qmdzy.qmdzy.service;

import com.example.qmdzy.qmdzy.model.CartItem;
import java.util.List;

public interface CartService {
    List<CartItem> getCartItems(Long userId);
    void addToCart(Long userId, Long productId, int quantity);
    void updateQuantity(Long userId, Long productId, int quantity);
    void removeFromCart(Long userId, Long productId);
    void clearCart(Long userId);
    CartItem getCartItemById(Long id);
    void removeFromCart(Long cartItemId);
}