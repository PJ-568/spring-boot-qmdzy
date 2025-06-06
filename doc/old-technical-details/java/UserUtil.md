# 旧项目实现细节·UserUtil

## 用户认证 - LoginCheck.java (old/src/main/java/UserUtil/LoginCheck.java)

### 功能实现

- 用户登录验证类，主要功能：
  - 处理 `/login` 路径的 POST 请求
  - 验证用户名和密码
  - 使用 Session 保存用户信息
  - 返回登录结果
- 技术要点：
  - 使用 Servlet API 实现
  - 直接操作 SQLite 数据库
  - 使用 HttpSession 保存状态
  - 包含基本的错误处理

### 重构建议

1. 移除对 Servlet API 的直接依赖
2. 使用加密存储和验证密码
3. 添加更完善的异常处理
4. 引入日志记录替代 System.out
5. 使用连接池管理数据库连接

### 新项目实现建议

在 Spring Boot 项目中：

1. 使用 Spring Security 框架替代
2. 实现 UserDetailsService 接口
3. 配置 PasswordEncoder 加密密码
4. 使用 JPA/Hibernate 操作数据库
5. 通过 SecurityConfig 配置认证流程
6. 使用 Thymeleaf 安全标签处理视图

## 用户注册 - SignUp.java (old/src/main/java/UserUtil/SignUp.java)

### 功能实现

- 用户注册类，主要功能：
  - 处理 `/sign_up` 路径的 POST 请求
  - 检查用户名是否已存在
  - 创建新用户记录
  - 设置默认用户角色为"user"
  - 自动登录新注册用户
- 技术要点：
  - 使用 Servlet API 实现
  - 直接操作 SQLite 数据库
  - 获取自增主键
  - 使用 HttpSession 保存状态

### 重构建议

1. 移除对 Servlet API 的直接依赖
2. 使用加密存储密码
3. 添加更完善的用户输入验证
4. 引入日志记录替代 System.out
5. 使用连接池管理数据库连接

### 新项目实现建议

在 Spring Boot 项目中：

1. 使用 Spring Security 的注册功能
2. 实现 UserDetails 接口
3. 配置 PasswordEncoder 加密密码
4. 使用 JPA/Hibernate 操作数据库
5. 通过 @Service 类处理业务逻辑
6. 使用 @Controller 处理 HTTP 请求
7. 添加表单验证注解

## 用户注销 - Logout.java (old/src/main/java/UserUtil/Logout.java)

### 功能实现

- 用户注销类，主要功能：
  - 处理 `/logout` 路径的 GET 和 POST 请求
  - 销毁当前用户会话
  - 重定向到登录页面
- 技术要点：
  - 使用 Servlet API 实现
  - 统一处理 GET/POST 请求
  - 安全的会话销毁机制

### 重构建议

1. 移除对 Servlet API 的直接依赖
2. 添加注销成功提示信息
3. 考虑添加 CSRF 保护
4. 引入日志记录

### 新项目实现建议

在 Spring Boot 项目中：

1. 使用 Spring Security 的注销功能
2. 通过 SecurityConfig 配置注销处理
3. 添加注销成功处理器
4. 使用 Thymeleaf 安全标签处理视图
5. 配置 CSRF 保护
6. 可添加注销确认页面

## 用户删除 - DelUser.java (old/src/main/java/UserUtil/DelUser.java)

### 功能实现

- 用户删除类，主要功能：
  - 处理 `/del_user` 路径的 GET 和 POST 请求
  - 验证当前用户会话
  - 删除用户数据
  - 销毁用户会话
  - 处理删除结果
- 技术要点：
  - 使用 Servlet API 实现
  - 会话状态检查
  - 统一处理 GET/POST 请求
  - 简单的删除操作模拟

### 重构建议

1. 实现实际的用户删除逻辑
2. 移除对 Servlet API 的直接依赖
3. 添加事务支持确保数据一致性
4. 引入日志记录
5. 添加更完善的错误处理

### 新项目实现建议

在 Spring Boot 项目中：

1. 使用 Spring Security 保护删除端点
2. 通过 @Service 类实现业务逻辑
3. 使用 JPA/Hibernate 实现数据删除
4. 配置事务管理
5. 添加权限检查(如 @PreAuthorize)
6. 使用 RESTful 风格API
7. 添加删除确认机制
