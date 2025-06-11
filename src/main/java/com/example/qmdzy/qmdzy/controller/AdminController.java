package com.example.qmdzy.qmdzy.controller;

import com.example.qmdzy.qmdzy.model.Category;
import com.example.qmdzy.qmdzy.model.Product;
import com.example.qmdzy.qmdzy.repository.CategoryRepository;
import com.example.qmdzy.qmdzy.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    public AdminController(ProductService productService, CategoryRepository categoryRepository) {
        this.productService = productService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String adminPage(Model model) {
        model.addAttribute("products", productService.findAllProducts());
        return "admin";
    }

    @PostMapping("/products")
    public String createProduct(@ModelAttribute Product product, @RequestParam String categoryName) {
        // 查找或创建分类
        List<Category> categories = categoryRepository.findByName(categoryName);
        Category category;
        
        if (categories.isEmpty()) {
            category = new Category(categoryName, "");
            category = categoryRepository.save(category);
        } else {
            category = categories.get(0);
        }
        
        // 设置商品分类并保存
        product.setCategory(category);
        productService.saveProduct(product);
        
        return "redirect:/admin";
    }

    @PostMapping("/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Optional<Product> productOpt = productService.findProductById(id);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            Category category = product.getCategory();
            
            if (category != null) {
                // 获取删除前的商品数量
                int productCount = category.getProducts().size();
                
                // 删除商品
                productService.deleteProduct(id);
                
                // 如果原商品数量为1，则删除类别
                if (productCount == 1) {
                    categoryRepository.delete(category);
                }
            } else {
                productService.deleteProduct(id);
            }
        }
        return "redirect:/admin";
    }
}