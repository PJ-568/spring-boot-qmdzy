# 旧项目实现细节·Servlet

## 业务逻辑 - cart.java (old/src/main/java/Servlet/cart.java)

### 功能实现

- 购物车控制器，处理购物车相关请求
- 主要功能：
  - 检查用户登录状态
  - 分页查询用户购物车项
  - 将数据转发到 JSP 页面展示
- 技术要点：
  - 使用 JDBC 直接操作数据库
  - 基于 Servlet 实现
  - 使用 Session 管理用户状态

### 重构建议

1. 将数据库操作抽离到单独的 Service 层
2. 使用 Spring MVC 替代原生 Servlet
3. 分页逻辑可以抽象为通用组件
4. 错误处理需要改进，避免直接暴露异常信息

### 新项目实现建议

在 Spring Boot 项目中：

1. 创建 CartController 使用 @RestController 或 @Controller
2. 实现 CartService 封装业务逻辑
3. 使用 Spring Data JPA 或 MyBatis 替代原生 JDBC
4. 添加统一异常处理机制
5. 考虑使用 Pageable 实现分页

## 业务逻辑 - order.java (old/src/main/java/Servlet/order.java)

### 功能实现

- 订单控制器，处理订单相关请求
- 主要功能：
  - 检查用户登录状态
  - 分页查询用户订单
  - 查询每个订单的订单项
  - 将数据转发到 JSP 页面展示
- 技术要点：
  - 使用 JDBC 直接操作数据库
  - 基于 Servlet 实现
  - 使用 Session 管理用户状态

### 重构建议

1. 将数据库操作抽离到 Service 层
2. 使用 Spring MVC 替代原生 Servlet
3. 分页逻辑可以抽象为通用组件
4. 订单项查询可以使用 JPA 的关联查询优化
5. 添加统一异常处理

### 新项目实现建议

在 Spring Boot 项目中：

1. 创建 OrderController 使用 @RestController 或 @Controller
2. 实现 OrderService 封装业务逻辑
3. 使用 Spring Data JPA 的关联查询优化订单项查询
4. 使用 Pageable 实现分页
5. 考虑添加订单状态机管理订单生命周期

## 业务逻辑 - detail.java (old/src/main/java/Servlet/detail.java)

### 功能实现

- 商品详情控制器，处理商品详情页请求
- 主要功能：
  - 根据商品ID查询商品详情
  - 查询商品所属分类名称
  - 将数据转发到 JSP 页面展示
- 技术要点：
  - 使用 JDBC 直接操作数据库
  - 基于 Servlet 实现
  - 直接拼接 SQL 查询
  - 手动管理数据库连接

### 重构建议

1. 将数据库操作抽离到 Service 层
2. 使用 Spring MVC 替代原生 Servlet
3. 使用 JPA 或 MyBatis 等 ORM 框架
4. 添加统一异常处理
5. 考虑使用缓存优化商品详情查询

### 新项目实现建议

在 Spring Boot 项目中：

1. 创建 ProductController 使用 @RestController 或 @Controller
2. 实现 ProductService 封装业务逻辑
3. 使用 Spring Data JPA 或 MyBatis 实现数据访问
4. 考虑使用 @Cacheable 注解实现商品详情缓存
5. 添加参数验证和统一异常处理

## 业务逻辑 - index.java (old/src/main/java/Servlet/index.java)

### 功能实现

- 首页控制器，处理商品列表展示请求
- 主要功能：
  - 分页查询商品列表(每页9个商品)
  - 支持按分类筛选商品
  - 查询所有分类信息
  - 获取当前分类名称(用于页面展示)
  - 将数据转发到 JSP 页面展示
- 技术要点：
  - 使用 JDBC 直接操作数据库
  - 基于 Servlet 实现
  - 使用 Model 类封装数据(Product, itemCategory)
  - 手动实现分页逻辑

### 重构建议

1. 将数据库操作抽离到 Service 层
2. 使用 Spring MVC 替代原生 Servlet
3. 使用 JPA 或 MyBatis 等 ORM 框架
4. 使用 Spring Data 的分页功能替代手动分页
5. 添加统一异常处理
6. 考虑使用缓存优化分类数据查询

### 新项目实现建议

在 Spring Boot 项目中：

1. 创建 HomeController 使用 @RestController 或 @Controller
2. 实现 ProductService 封装商品查询逻辑
3. 实现 CategoryService 封装分类查询逻辑
4. 使用 Spring Data JPA 的 Pageable 实现分页
5. 使用 @Cacheable 注解缓存分类数据
6. 添加参数验证和统一异常处理

## 业务逻辑 - manage.java (old/src/main/java/Servlet/manage.java)

### 功能实现

- 后台管理控制器，处理管理相关请求
- 主要功能：
  - 验证用户登录状态和管理员权限
  - 执行用户输入的SQL查询并返回结果
  - 将结果以HTML表格形式展示
- 技术要点：
  - 使用 Session 管理用户状态
  - 直接执行用户输入的SQL语句(存在SQL注入风险)
  - 使用原生JDBC操作数据库
  - 手动拼接HTML返回结果

### 重构建议

1. 移除直接执行用户SQL的功能(严重安全隐患)
2. 将数据库操作抽离到Service层
3. 使用Spring Security处理权限验证
4. 添加细粒度的权限控制
5. 使用ORM框架替代原生JDBC
6. 添加操作日志记录
7. 实现安全的数据库管理功能

### 新项目实现建议

在 Spring Boot 项目中：

1. 创建 AdminController 使用 @RestController 或 @Controller
2. 使用 @PreAuthorize 注解实现权限控制
3. 实现 AdminService 封装安全的管理功能
4. 使用 Spring Data JPA 或 MyBatis 实现数据访问
5. 添加操作审计日志
6. 实现以下安全功能：
   - 预定义的数据库操作接口
   - 参数化查询
   - 结果集大小限制
   - 敏感操作二次验证

## 业务逻辑 - test.java (old/src/main/java/Servlet/test.java)

### 功能实现

- 测试控制器，用于展示所有商品数据
- 主要功能：
  - 查询所有商品信息
  - 以HTML表格形式返回结果
- 技术要点：
  - 使用原生JDBC操作数据库
  - 手动拼接HTML返回结果
  - 直接输出到response writer

### 重构建议

1. 此Servlet仅用于测试，不建议在新项目中保留
2. 如需类似功能，应：
   - 使用专门的测试框架
   - 实现标准化的API接口
   - 使用模板引擎生成HTML

### 新项目实现建议

在 Spring Boot 项目中：

1. 使用Spring Boot Test框架编写测试
2. 如需查询展示功能：
   - 创建标准API接口
   - 使用Thymeleaf模板引擎
   - 实现分页查询
3. 避免直接输出HTML到response
