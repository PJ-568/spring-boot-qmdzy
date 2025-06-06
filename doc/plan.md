# Spring Boot 在线商店项目规划

## 1. 技术选型

- **核心框架**: Spring Boot 3.x
- **模板引擎**: Thymeleaf
- **安全框架**: Spring Security
- **持久层**: Spring Data JPA + Hibernate
- **数据库**: SQLite
- **缓存**: Spring Cache (内存缓存)
- **构建工具**: Maven

## 2. 项目结构

```plaintext
src/
├── main/
│   ├── java/com/example/qmdzy/
│   │   ├── config/       # 配置类
│   │   ├── controller/   # 控制器
│   │   ├── model/        # 数据模型(实体)
│   │   ├── repository/   # 数据访问层  
│   │   ├── service/      # 业务服务层
│   │   ├── util/         # 工具类
│   │   └── QmdzyApplication.java # 启动类
│   └── resources/
│       ├── static/       # 静态资源
│       ├── templates/    # Thymeleaf模板
│       └── application.properties
└── test/                 # 测试代码
```

## 3. 模块实现规划

### 3.1 用户模块 (原UserUtil)

- **实现方案**:
  - 使用Spring Security处理认证授权
  - 密码加密存储(BCryptPasswordEncoder)
  - 角色权限管理
- **对应旧项目**:
  - LoginCheck → SecurityConfig + UserDetailsService
  - SignUp → AuthController + UserService
  - Logout → SecurityConfig配置

### 3.2 商品模块 (原Model/Product)

- **实现方案**:
  - Product实体类使用JPA注解
  - 商品分类关联关系
  - 商品搜索/分页功能
- **对应旧项目**:
  - Product.java → Product实体类
  - detail.java → ProductController

### 3.3 购物车模块 (原Carts)

- **实现方案**:
  - Redis缓存购物车数据
  - 分布式Session支持
  - 商品库存校验
- **对应旧项目**:
  - AddCart.java → CartController
  - DelCart.java → CartController

### 3.4 订单模块 (原Order)

- **实现方案**:
  - 事务管理(@Transactional)
  - 订单状态机
  - 订单项关联商品
- **对应旧项目**:
  - AddOrder.java → OrderController
  - order.java → OrderController

## 4. 依赖配置(pom.xml)

```xml
<dependencies>
    <!-- Spring Boot Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-cache</artifactId>
    </dependency>
    
    <!-- SQLite Database -->
    <dependency>
        <groupId>org.xerial</groupId>
        <artifactId>sqlite-jdbc</artifactId>
        <version>3.42.0.0</version>
    </dependency>
    
    <!-- Other -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>
</dependencies>
```

## 5. 安全与缓存配置

### 5.1 安全配置(SecurityConfig)

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/cart/**", "/order/**").authenticated()
                .anyRequest().permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/")
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/")
            );
        return http.build();
    }
}
```

### 5.2 缓存配置

```java
@Configuration
@EnableCaching
public class CacheConfig {
    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("products", "categories");
    }
}
```

## 6. 开发计划

1. **第一阶段**: 基础框架搭建
   - 初始化Spring Boot项目
   - 配置安全、数据库、缓存
   - 实现用户认证模块

2. **第二阶段**: 核心功能开发
   - 商品管理功能
   - 购物车功能
   - 订单功能

3. **第三阶段**: 优化与测试
   - 前端页面优化
   - 性能测试与调优
   - 安全加固
