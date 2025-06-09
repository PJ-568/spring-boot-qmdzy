package com.example.qmdzy.qmdzy.repository;

import com.example.qmdzy.qmdzy.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}