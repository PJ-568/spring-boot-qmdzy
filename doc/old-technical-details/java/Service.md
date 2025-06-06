# 旧项目实现细节·Service

## 购物车服务 - AddCart.java (old/src/main/java/Service/AddCart.java)

### 功能实现

- 购物车添加商品服务，主要功能：
  - 处理用户添加商品到购物车的请求
  - 检查商品库存是否充足
  - 更新购物车中商品数量(新增或累加)
  - 处理用户未登录、商品不存在等异常情况
- 技术要点：
  - 基于 Servlet 实现
  - 使用 JDBC 直接操作数据库
  - 会话管理检查用户登录状态
  - 完整的异常处理和资源释放

### 重构建议

1. 将业务逻辑从 Servlet 中分离出来
2. 使用 Spring 的声明式事务管理
3. 将数据库操作封装到 Repository 层
4. 使用 Spring MVC 的注解替代原生 Servlet API
5. 添加更完善的输入验证

### 新项目实现建议

在 Spring Boot 项目中：

1. 使用 @Controller 或 @RestController 注解创建控制器
2. 通过 @Service 层处理业务逻辑
3. 使用 Spring Data JPA 或 MyBatis 管理数据库访问
4. 通过 @Transactional 管理事务
5. 使用 Thymeleaf 模板处理视图
6. 通过 Spring Security 管理用户认证

## 订单服务 - AddOrder.java (old/src/main/java/Service/AddOrder.java)

### 功能实现

- 订单创建服务，主要功能：
  - 处理用户从购物车创建订单的请求
  - 检查商品库存是否充足
  - 创建订单记录和订单项
  - 更新商品库存并删除购物车项
  - 处理用户未登录、商品不存在等异常情况
- 技术要点：
  - 基于 Servlet 实现
  - 使用 JDBC 直接操作数据库
  - 手动管理事务(没有回滚机制)
  - 使用 Order 和 OrderItem 模型类
  - 完整的异常处理和资源释放

### 重构建议

1. 添加事务回滚机制确保数据一致性
2. 将业务逻辑从 Servlet 中分离出来
3. 使用 Spring 的声明式事务管理
4. 将数据库操作封装到 Repository 层
5. 添加更完善的输入验证

### 新项目实现建议

在 Spring Boot 项目中：

1. 使用 @Transactional 注解确保事务一致性
2. 通过 @Service 层处理订单业务逻辑
3. 使用 Spring Data JPA 管理订单和订单项
4. 通过 Spring Cache 优化库存检查
5. 使用 Thymeleaf 模板处理订单确认页面
6. 通过 Spring Security 保护订单创建端点

## 购物车删除服务 - DelCart.java (old/src/main/java/Service/DelCart.java)

### 功能实现

- 购物车删除商品服务，主要功能：
  - 处理用户从购物车删除商品的请求
  - 验证用户权限(只能删除自己的购物车项)
  - 处理用户未登录等异常情况
- 技术要点：
  - 基于 Servlet 实现
  - 使用 JDBC 直接操作数据库
  - 会话管理检查用户登录状态
  - 简单的异常处理和资源释放

### 重构建议

1. 添加参数化查询防止 SQL 注入
2. 将业务逻辑从 Servlet 中分离出来
3. 使用 Spring 的声明式事务管理
4. 将数据库操作封装到 Repository 层
5. 添加更完善的输入验证

### 新项目实现建议

在 Spring Boot 项目中：

1. 使用 @DeleteMapping 注解创建 RESTful 端点
2. 通过 @Service 层处理删除业务逻辑
3. 使用 Spring Data JPA 管理购物车项删除
4. 通过 Spring Security 保护删除端点
5. 使用 ResponseEntity 返回标准化的响应

## 商品名称查询服务 - GetName.java (old/src/main/java/Service/GetName.java)

### 功能实现

- 商品名称查询服务，主要功能：
  - 根据商品ID查询商品名称
  - 处理无效ID等异常情况
  - 直接返回商品名称文本(非HTML)
- 技术要点：
  - 基于 Servlet 实现
  - 使用 JDBC 直接操作数据库
  - 简单的异常处理和资源释放
  - 直接输出响应内容

### 重构建议

1. 添加缓存机制减少数据库查询
2. 将业务逻辑从 Servlet 中分离出来
3. 使用 Spring 的声明式事务管理
4. 将数据库操作封装到 Repository 层
5. 添加更完善的输入验证

### 新项目实现建议

在 Spring Boot 项目中：

1. 使用 @GetMapping 注解创建 RESTful 端点
2. 通过 @Service 层处理查询逻辑
3. 使用 Spring Data JPA 管理商品查询
4. 通过 Spring Cache 缓存商品名称
5. 使用 @ResponseBody 返回标准化的 JSON 响应
