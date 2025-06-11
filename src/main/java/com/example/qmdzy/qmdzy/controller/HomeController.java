package com.example.qmdzy.qmdzy.controller;

import com.example.qmdzy.qmdzy.model.Category;
import com.example.qmdzy.qmdzy.model.Product;
import com.example.qmdzy.qmdzy.repository.CategoryRepository;
import com.example.qmdzy.qmdzy.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    public HomeController(ProductService productService, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/")
    public String home(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "cate", defaultValue = "0") Long categoryId,
            Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("username", auth.getName());
        model.addAttribute("roles", auth.getAuthorities());
        
        // 每页显示6个商品
        Pageable pageable = PageRequest.of(page - 1, 6);
        Page<Product> productPage;
        
        if (categoryId == 0) {
            productPage = productService.findAllProducts(pageable);
        } else {
            Optional<Category> category = categoryRepository.findById(categoryId);
            if (category.isPresent()) {
                productPage = productService.findByCategory(category.get(), pageable);
            } else {
                productPage = productService.findAllProducts(pageable);
            }
        }
        
        List<Category> categories = categoryRepository.findAll();
        
        model.addAttribute("products", productPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("categories", categories);
        model.addAttribute("currentCate", categoryId);
        
        return "home";
    }
}