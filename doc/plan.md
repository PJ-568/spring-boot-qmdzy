# Spring Boot 在线商店项目规划

## 特性

### 用户类型

未登录用户：可访问首页、商品详情、搜索、注册、登录功能。
已登录用户：可访问首页、商品详情、搜索、购物车、订单功能、用户中心页。
管理员：在已登录用户的基础上还可访问管理员管理页面。

### 页面功能

- 首页：分页展示商品分类，支持点击跳转到对应商品详情。展示指定类别商品。
- 搜索功能：支持模糊搜索，按分类、价格区间、库存量等条件进行搜索。
- 商品详情页：展示商品的详细信息，包括图片、价格、描述等。提供加入购物车的选项。
- 购物车：允许用户查看已加入购物车的商品，并进行批量购买。支持删除。购买后，跳转到订单页。
- 订单页：支持订单信息查询。
- 管理员管理页面：管理员可通过执行 SQL 命令来管理商品、用户等。
- 注册页：用户注册功能。
- 登录页：用户登录功能。
- 用户中心页：展示用户的个人信息，可选择修改个人信息、退出登录、删除账户。

## 1. 技术选型

- **核心框架**: Spring Boot 3.x
- **模板引擎**: Thymeleaf
- **安全框架**: Spring Security
- **持久层**: Spring Data JPA + Hibernate
- **数据库**: H2 内存数据库
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

<!-- TODO 网络搜索教程获取 -->

## 5. 安全与缓存配置

### 5.1 安全配置(SecurityConfig)

<!-- TODO 网络搜索教程获取 -->

### 5.2 缓存配置

<!-- TODO 网络搜索教程获取 -->

## 6. 开发计划

1. **第一阶段**: 基础框架搭建
   - 初始化Spring Boot项目
   - 配置安全、数据库、缓存
   - 实现用户认证模块
   ……

2. **第二阶段**: 核心功能开发
   - 商品管理功能
   - 购物车功能
   - 订单功能
   ……

3. **第三阶段**: 优化与测试
   - 前端页面优化
   - 性能测试与调优
   - 安全加固
   ……
