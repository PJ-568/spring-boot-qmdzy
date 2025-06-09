package com.example.qmdzy.qmdzy.repository;

import com.example.qmdzy.qmdzy.model.Category;
import com.example.qmdzy.qmdzy.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // 根据分类分页查询商品
    Page<Product> findByCategory(Category category, Pageable pageable);
}