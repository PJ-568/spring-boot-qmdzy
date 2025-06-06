# 旧项目实现细节·DBUtil

## 工具类 - DBInitializer.java (old/src/main/java/DBUtil/DBInitializer.java)

### 功能实现

- 数据库初始化工具类，主要功能：
  - 加载 SQLite JDBC 驱动
  - 管理数据库连接生命周期
  - 应用启动时初始化数据库
  - 提供全局数据库连接获取方法
- 技术要点：
  - 实现 ServletContextListener 监听应用生命周期
  - 使用静态方法提供数据库连接
  - 包含连接状态检查功能

### 重构建议

1. 移除对 Servlet API 的依赖，改为纯 JDBC 工具类
2. 添加连接池支持(如 HikariCP)
3. 配置文件外置化(数据库路径、驱动类等)
4. 添加更完善的异常处理

### 新项目实现建议

在 Spring Boot 项目中：

1. 使用 Spring Data JPA 或 MyBatis 替代原生 JDBC
2. 配置 HikariCP 连接池
3. 通过 application.properties 配置数据源
4. 使用 @Configuration 类管理数据源配置
5. 考虑使用 Flyway 或 Liquibase 管理数据库迁移

## 数据库初始化 - InitDB.java (old/src/main/java/DBUtil/InitDB.java)

### 功能实现

- 数据库初始化类，主要功能：
  - 删除并重新创建数据库文件
  - 创建所有数据表结构
  - 插入初始测试数据(用户、分类、商品、订单等)
- 技术要点：
  - 使用原生 JDBC 执行 SQL
  - 包含完整的数据库架构定义
  - 提供丰富的测试数据

### 重构建议

1. 将 SQL 语句外置到单独的文件中
2. 使用数据库迁移工具(如 Flyway)管理表结构
3. 测试数据应该与业务代码分离
4. 添加事务支持确保数据一致性

### 新项目实现建议

在 Spring Boot 项目中：

1. 使用 Flyway 或 Liquibase 管理数据库迁移
2. 在测试配置中初始化测试数据
3. 通过 application.properties 配置数据库
4. 使用 @Sql 注解加载测试数据
5. 考虑使用测试数据库替代生产数据库
