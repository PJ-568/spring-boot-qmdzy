# 旧项目实现细节·Model

## 数据模型 - Product.java (old/src/main/java/Model/Product.java)

### 功能实现

- 产品实体类，包含以下属性：
  - id: 产品ID
  - name: 产品名称
  - description: 产品描述
  - price: 产品价格
  - stock: 库存数量
  - categoryId: 分类ID
- 提供无参和全参构造函数
- 为所有属性提供标准的 getter 和 setter 方法

### 重构建议

建议重构为 JPA 实体类，添加：

- `@Entity` 注解
- `@Id` 和 `@GeneratedValue` 注解主键
- 与 Category 的一对多关系注解
- 数据验证注解如 `@NotBlank`, `@PositiveOrZero` 等

### 新项目实现建议

在 Spring Boot 项目中：

1. 创建 `Product` 实体类，使用 JPA 注解
2. 添加与 `Category` 的关联关系
3. 考虑使用 Lombok 简化 getter/setter
4. 实现 Repository 接口继承 JpaRepository

## 数据模型 - User.java (old/src/main/java/Model/User.java)

### 功能实现

- 用户实体类，包含以下属性：
  - id: 用户ID
  - username: 用户名
  - nickname: 昵称
  - password: 密码(明文存储，不安全)
  - role: 角色标识(0-普通用户，1-管理员)
- 提供全参构造函数
- 为所有属性提供标准的 getter 和 setter 方法

### 重构建议

1. 密码应加密存储，使用 BCryptPasswordEncoder
2. 建议重构为 JPA 实体类，添加：
   - `@Entity` 注解
   - `@Id` 和 `@GeneratedValue` 注解主键
   - 角色字段可使用枚举类型
3. 添加数据验证注解如 `@NotBlank`, `@Size` 等

### 新项目实现建议

在 Spring Boot 项目中：

1. 实现 `User` 实体类，使用 Spring Security 的用户详情接口
2. 添加密码加密机制
3. 定义角色枚举或使用 Spring Security 的角色权限系统
4. 实现 UserDetailsService 接口
5. 创建 UserRepository 继承 JpaRepository

## 数据模型 - Carts.java (old/src/main/java/Model/Carts.java)

### 功能实现

- 购物车项实体类，包含以下属性：
  - id: 购物车项ID
  - product_id: 关联产品ID
  - product_name: 产品名称
  - product_description: 产品描述
  - price: 产品单价
  - amount: 产品数量
- 提供无参和全参构造函数
- 为所有属性提供标准的 getter 和 setter 方法
- 包含业务方法：
  - increaseAmount(): 增加商品数量
  - decreaseAmount(): 减少商品数量(带数量校验)

### 重构建议

1. 建议重构为 JPA 实体类，与 Product 建立多对一关系
2. 移除冗余字段(product_name, product_description)，通过关联查询获取
3. 可考虑使用 BigDecimal 替代 double 处理价格
4. 添加数据验证注解

### 新项目实现建议

在 Spring Boot 项目中：

1. 创建 `CartItem` 实体类，与 `Product` 建立关联关系
2. 实现 CartService 处理购物车业务逻辑
3. 使用 Spring Data JPA 实现数据访问
4. 考虑使用 Redis 实现购物车缓存

## 数据模型 - Order.java (old/src/main/java/Model/Order.java)

### 功能实现

- 订单实体类，包含以下属性：
  - id: 订单ID
  - userId: 用户ID
  - totalAmount: 订单总金额
  - createdAt: 创建时间
  - orderItems: 订单项列表(关联 OrderItem)
- 提供无参和全参构造函数
- 为所有属性提供标准的 getter 和 setter 方法
- 包含业务方法：
  - addOrderItem(): 添加订单项并更新总金额
  - removeOrderItem(): 移除订单项并更新总金额

### 重构建议

1. 建议重构为 JPA 实体类：
   - 使用 `@OneToMany` 关联 OrderItem
   - 添加 `@CreationTimestamp` 自动设置创建时间
2. 使用 BigDecimal 替代 double 处理金额
3. 添加数据验证注解
4. 考虑将业务方法移到 Service 层

### 新项目实现建议

在 Spring Boot 项目中：

1. 创建 `Order` 实体类，使用 JPA 注解
2. 实现 OrderRepository 继承 JpaRepository
3. 创建 OrderService 处理订单业务逻辑
4. 考虑使用事件驱动架构处理订单创建后的业务

## 数据模型 - itemCategory.java (old/src/main/java/Model/itemCategory.java)

### 功能实现

- 商品分类实体类，包含以下属性：
  - id: 分类ID
  - name: 分类名称
  - description: 分类描述
- 提供全参构造函数
- 为所有属性提供标准的 getter 和 setter 方法

### 重构建议

1. 建议重构为 JPA 实体类：
   - 添加 `@Entity` 注解
   - 使用 `@Id` 和 `@GeneratedValue` 注解主键
2. 添加数据验证注解如 `@NotBlank`, `@Size` 等
3. 与 Product 类建立一对多关系

### 新项目实现建议

在 Spring Boot 项目中：

1. 创建 `Category` 实体类，使用 JPA 注解
2. 实现 CategoryRepository 继承 JpaRepository
3. 考虑使用缓存优化分类数据的读取

## 数据模型 - OrderItem.java (old/src/main/java/Model/OrderItem.java)

### 功能实现

- 订单项实体类，包含以下属性：
  - id: 订单项ID
  - orderId: 关联订单ID
  - productId: 关联产品ID
  - quantity: 产品数量
  - price: 产品单价
- 提供无参和全参构造函数
- 为所有属性提供标准的 getter 和 setter 方法
- 重写 toString() 方法用于调试

### 重构建议

1. 建议重构为 JPA 实体类：
   - 使用 `@ManyToOne` 关联 Order 和 Product
   - 使用 `@CreationTimestamp` 记录创建时间
2. 使用 BigDecimal 替代 double 处理价格
3. 添加数据验证注解

### 新项目实现建议

在 Spring Boot 项目中：

1. 创建 `OrderItem` 实体类，与 `Order` 和 `Product` 建立关联关系
2. 实现 OrderItemRepository 继承 JpaRepository
3. 考虑使用 DTO 模式处理订单项数据传输
