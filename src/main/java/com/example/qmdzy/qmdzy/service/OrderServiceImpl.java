package com.example.qmdzy.qmdzy.service;

import com.example.qmdzy.qmdzy.model.*;
import com.example.qmdzy.qmdzy.repository.OrderRepository;
import com.example.qmdzy.qmdzy.repository.ProductRepository;
import com.example.qmdzy.qmdzy.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
            CartService cartService,
            UserRepository userRepository,
            ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Order createOrder(Long userId, List<Long> cartItemIds) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        List<CartItem> cartItems = cartItemIds.stream()
                .map(id -> cartService.getCartItemById(id))
                .collect(Collectors.toList());

        Order order = new Order();
        order.setUser(user);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> {
                    // 更新商品库存
                    Product product = cartItem.getProduct();
                    product.setStock(product.getStock() - cartItem.getQuantity());
                    productRepository.save(product);

                    OrderItem orderItem = new OrderItem();
                    orderItem.setProduct(product);
                    orderItem.setQuantity(cartItem.getQuantity());
                    orderItem.setPrice(product.getPrice());
                    return orderItem;
                }).collect(Collectors.toList());

        order.setItems(orderItems);

        BigDecimal totalAmount = orderItems.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(totalAmount);

        // 清空购物车中已下单的商品
        cartItemIds.forEach(cartService::removeFromCart);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}