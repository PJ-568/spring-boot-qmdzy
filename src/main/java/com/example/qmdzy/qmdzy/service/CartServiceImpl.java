package com.example.qmdzy.qmdzy.service;

import com.example.qmdzy.qmdzy.model.CartItem;
import com.example.qmdzy.qmdzy.model.Product;
import com.example.qmdzy.qmdzy.model.User;
import com.example.qmdzy.qmdzy.repository.CartRepository;
import com.example.qmdzy.qmdzy.repository.ProductRepository;
import com.example.qmdzy.qmdzy.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(CartRepository cartRepository,
                          ProductRepository productRepository,
                          UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<CartItem> getCartItems(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public void addToCart(Long userId, Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("商品不存在"));
        
        CartItem existingItem = cartRepository.findByUserIdAndProductId(userId, productId);
        if (existingItem != null) {
            existingItem.increaseQuantity(quantity);
            cartRepository.save(existingItem);
        } else {
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
            CartItem newItem = new CartItem(product, quantity, user);
            cartRepository.save(newItem);
        }
    }

    @Override
    public void updateQuantity(Long userId, Long productId, int quantity) {
        CartItem item = cartRepository.findByUserIdAndProductId(userId, productId);
        if (item != null) {
            item.setQuantity(quantity);
            cartRepository.save(item);
        }
    }

    @Override
    public void removeFromCart(Long userId, Long productId) {
        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }

    @Override
    public void clearCart(Long userId) {
        cartRepository.deleteByUserId(userId);
    }
}