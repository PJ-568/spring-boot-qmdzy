package com.example.qmdzy.qmdzy.repository;

import com.example.qmdzy.qmdzy.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    // 根据名称查找分类
    List<Category> findByName(String name);
}