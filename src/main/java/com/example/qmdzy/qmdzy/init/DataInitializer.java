package com.example.qmdzy.qmdzy.init;

import com.example.qmdzy.qmdzy.model.Category;
import com.example.qmdzy.qmdzy.model.Product;
import com.example.qmdzy.qmdzy.model.User;
import com.example.qmdzy.qmdzy.repository.CategoryRepository;
import com.example.qmdzy.qmdzy.repository.ProductRepository;
import com.example.qmdzy.qmdzy.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DataInitializer(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,
                          CategoryRepository categoryRepository,
                          ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // 初始化管理员用户
        if (!userRepository.existsByUsername("202235010623")) {
            User admin = new User();
            admin.setUsername("202235010623");
            admin.setPassword(passwordEncoder.encode("202235010623"));
            admin.setNickname("PJ568");
            admin.setRole(1); // 1 表示管理员
            userRepository.save(admin);
        }
        if (!userRepository.existsByUsername("202235010611")) {
            User admin = new User();
            admin.setUsername("202235010611");
            admin.setPassword(passwordEncoder.encode("202235010611"));
            admin.setNickname("风衣");
            admin.setRole(1); // 1 表示管理员
            userRepository.save(admin);
        }
        if (!userRepository.existsByUsername("202235010649")) {
            User admin = new User();
            admin.setUsername("202235010649");
            admin.setPassword(passwordEncoder.encode("202235010649"));
            admin.setNickname("烨灼");
            admin.setRole(1); // 1 表示管理员
            userRepository.save(admin);
        }

        // 初始化普通用户
        if (!userRepository.existsByUsername("user")) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setNickname("普通用户");
            user.setRole(0); // 0 表示普通用户
            userRepository.save(user);
        }

        // 初始化商品分类
        if (categoryRepository.count() == 0) {
            List<Category> categories = Arrays.asList(
                new Category("电子产品", "包括各种消费电子设备，如智能手机、平板电脑、笔记本电脑、电视等。"),
                new Category("书籍", "涵盖各种类型的书籍，包括小说、教科书、参考书、杂志等。"),
                new Category("服装", "提供各种风格和类别的服装，如休闲装、正装、运动装等。"),
                new Category("家用电器", "包括厨房电器、清洁电器、个人护理电器等，满足家庭日常需求。"),
                new Category("玩具", "适合各个年龄段的玩具，包括益智玩具、户外玩具、模型等。"),
                new Category("运动与户外用品", "提供各种运动装备和户外活动用品，如健身器材、露营装备、运动服装等。"),
                new Category("美妆和个人护理", "涵盖护肤品、彩妆、洗护用品等，帮助用户保持美丽和健康。"),
                new Category("食品和高端美食", "提供各种食品和饮品，包括健康食品、进口食品、高端美食等。"),
                new Category("健康与家居用品", "包括健康监测设备、家居装饰品、清洁用品等，提升生活质量。"),
                new Category("宠物用品", "涵盖宠物食品、玩具、护理用品等，满足宠物日常需求。")
            );
            
            categoryRepository.saveAll(categories);
        }

        // 初始化商品
        if (productRepository.count() == 0) {
            List<Product> products = Arrays.asList(
                // 电子产品
                new Product("笔记本电脑", "高性能笔记本电脑，适合办公和娱乐", new BigDecimal("3999.99"), 10, getCategoryByName("电子产品")),
                new Product("智能手机", "最新款智能手机，拍照性能卓越", new BigDecimal("3499.99"), 20, getCategoryByName("电子产品")),
                new Product("平板电脑", "轻薄便携的平板电脑", new BigDecimal("1999.99"), 30, getCategoryByName("电子产品")),
                new Product("智能手表", "多功能智能手表", new BigDecimal("999.99"), 40, getCategoryByName("电子产品")),
                
                // 书籍
                new Product("科幻小说", "经典科幻小说，适合科幻迷", new BigDecimal("69.99"), 50, getCategoryByName("书籍")),
                new Product("文学名著", "世界名著，值得收藏", new BigDecimal("39.99"), 40, getCategoryByName("书籍")),
                new Product("历史书籍", "精彩历史故事", new BigDecimal("59.99"), 30, getCategoryByName("书籍")),
                new Product("科技书籍", "最新科技前沿", new BigDecimal("79.99"), 25, getCategoryByName("书籍")),
                
                // 服装
                new Product("T恤", "纯棉T恤，夏季必备", new BigDecimal("13.62"), 100, getCategoryByName("服装")),
                new Product("牛仔裤", "经典牛仔裤，百搭时尚", new BigDecimal("59.99"), 80, getCategoryByName("服装")),
                new Product("卫衣", "舒适卫衣", new BigDecimal("129.99"), 60, getCategoryByName("服装")),
                new Product("运动鞋", "轻便运动鞋", new BigDecimal("299.99"), 40, getCategoryByName("服装")),
                
                // 家用电器
                new Product("电冰箱", "大容量电冰箱，保鲜效果好", new BigDecimal("1299.99"), 15, getCategoryByName("家用电器")),
                new Product("洗衣机", "高效洗衣机，省水节能", new BigDecimal("899.99"), 25, getCategoryByName("家用电器")),
                new Product("微波炉", "快速加热", new BigDecimal("499.99"), 20, getCategoryByName("家用电器")),
                new Product("电饭煲", "智能电饭煲", new BigDecimal("299.99"), 30, getCategoryByName("家用电器")),
                new Product("可控核聚变反应堆", "商用托卡马克装置，用于发电", new BigDecimal("999999.99"), 10, getCategoryByName("家用电器")),
                
                // 玩具
                new Product("遥控汽车", "遥控汽车，适合儿童", new BigDecimal("19.99"), 60, getCategoryByName("玩具")),
                new Product("拼图", "益智拼图，锻炼思维", new BigDecimal("9.99"), 70, getCategoryByName("玩具")),
                new Product("炫彩积木", "积木拼图，锻炼思维", new BigDecimal("19.99"), 50, getCategoryByName("玩具")),
                new Product("飞行机", "飞行机，玩具飞机", new BigDecimal("99.99"), 30, getCategoryByName("玩具")),
                new Product("变形金刚", "可变形玩具", new BigDecimal("129.99"), 30, getCategoryByName("玩具")),
                new Product("芭比娃娃", "经典芭比娃娃", new BigDecimal("89.99"), 50, getCategoryByName("玩具")),
                
                // 运动与户外用品
                new Product("瑜伽垫", "专业瑜伽垫，适合各种瑜伽练习", new BigDecimal("199.99"), 50, getCategoryByName("运动与户外用品")),
                new Product("登山杖", "轻便耐用登山杖", new BigDecimal("149.99"), 40, getCategoryByName("运动与户外用品")),
                
                // 美妆和个人护理
                new Product("防晒霜", "高倍数防晒霜，有效阻挡紫外线", new BigDecimal("99.99"), 30, getCategoryByName("美妆和个人护理")),
                
                // 食品和高端美食
                new Product("有机蔬菜", "新鲜有机蔬菜，健康选择", new BigDecimal("49.99"), 20, getCategoryByName("食品和高端美食")),
                
                // 健康与家居用品
                new Product("空气净化器", "高效空气净化器，改善室内空气质量", new BigDecimal("299.99"), 15, getCategoryByName("健康与家居用品")),
                
                // 宠物用品
                new Product("宠物狗粮", "高质量宠物狗粮，营养均衡", new BigDecimal("59.99"), 40, getCategoryByName("宠物用品"))
            );
            
            productRepository.saveAll(products);
        }
    }
    
    private Category getCategoryByName(String name) {
        return categoryRepository.findByName(name).get(0);
    }
}