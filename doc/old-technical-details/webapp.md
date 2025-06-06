# 旧项目实现细节·webapp

## 页面 - cart_back.jsp (old/src/main/webapp/cart_back.jsp)

### 功能实现

- 购物车页面，主要功能：
  - 展示用户购物车商品列表
  - 支持分页显示
  - 提供删除和购买功能
- 技术要点：
  - 使用 JSP + JSTL 渲染页面
  - 包含 navbar 和 footer 公共组件
  - 表单提交处理购物车操作
  - 基本的分页导航

### 重构建议

1. 移除 JSP 改用现代模板引擎(如 Thymeleaf)
2. 将业务逻辑从页面中分离
3. 使用 AJAX 优化删除和购买操作
4. 添加购物车商品数量修改功能
5. 优化分页组件

### 新项目实现建议

在 Spring Boot + Thymeleaf 项目中：

1. 创建 cart.html 使用 Thymeleaf 模板
2. 使用 Fragment 复用公共组件
3. 通过 CartController 提供数据
4. 使用 AJAX 处理购物车操作
5. 添加购物车商品数量实时更新功能
6. 使用 Bootstrap 优化页面布局

## 页面 - index_back.jsp (old/src/main/webapp/index_back.jsp)

### 功能实现

- 商城首页，主要功能：
  - 展示商品列表
  - 支持按分类筛选
  - 分页显示商品
  - 商品详情页跳转
- 技术要点：
  - 使用 JSP + JSTL 渲染页面
  - 包含 navbar 和 footer 公共组件
  - 分类筛选下拉菜单
  - 基本的分页导航

### 重构建议

1. 移除 JSP 改用现代模板引擎(如 Thymeleaf)
2. 将业务逻辑从页面中分离
3. 使用 AJAX 实现分类筛选和分页
4. 优化商品卡片布局和样式
5. 添加搜索功能

### 新项目实现建议

在 Spring Boot + Thymeleaf 项目中：

1. 创建 index.html 使用 Thymeleaf 模板
2. 使用 Fragment 复用公共组件
3. 通过 ProductController 提供数据
4. 使用 AJAX 实现分类筛选和分页
5. 添加商品搜索功能
6. 使用 Bootstrap 优化页面布局和响应式设计

## 页面 - detail_back.jsp (old/src/main/webapp/detail_back.jsp)

### 功能实现

- 商品详情页，主要功能：
  - 展示商品详细信息
  - 显示库存数量
  - 提供加入购物车功能(需登录)
  - 未登录用户提示登录
- 技术要点：
  - 使用 JSP + JSTL 渲染页面
  - 包含 navbar 和 footer 公共组件
  - 根据登录状态显示不同内容
  - 数量选择器限制最大值为库存数量

### 重构建议

1. 移除 JSP 改用现代模板引擎(如 Thymeleaf)
2. 将业务逻辑从页面中分离
3. 使用 AJAX 处理加入购物车操作
4. 优化商品图片展示
5. 添加商品评价功能

### 新项目实现建议

在 Spring Boot + Thymeleaf 项目中：

1. 创建 detail.html 使用 Thymeleaf 模板
2. 使用 Fragment 复用公共组件
3. 通过 ProductController 提供数据
4. 使用 AJAX 处理加入购物车操作
5. 添加商品评价模块
6. 使用 Bootstrap 优化页面布局

## 页面 - manage_back.jsp (old/src/main/webapp/manage_back.jsp)

### 功能实现

- 管理页面，主要功能：
  - 提供管理员测试界面
  - 支持直接执行 SQL 命令
  - 显示命令执行结果
  - 包含数据库表结构参考文档
- 技术要点：
  - 使用 JSP + JSTL 渲染页面
  - 包含 navbar 和 footer 公共组件
  - 表单提交处理 SQL 命令
  - 显示完整的数据库架构文档

### 重构建议

1. 移除 JSP 改用现代模板引擎(如 Thymeleaf)
2. 移除直接执行 SQL 的功能(安全风险)
3. 添加权限验证(仅管理员可访问)
4. 将数据库文档外置化
5. 提供更安全的管理功能接口

### 新项目实现建议

在 Spring Boot + Thymeleaf 项目中：

1. 创建 admin.html 使用 Thymeleaf 模板
2. 使用 Fragment 复用公共组件
3. 通过 AdminController 提供数据
4. 使用 Spring Security 保护管理接口
5. 实现安全的商品/用户管理功能
6. 添加操作日志记录功能

## 页面 - user.jsp (old/src/main/webapp/user.jsp)

### 功能实现

- 用户登录/注册页面，主要功能：
  - 用户登录表单(用户名/密码)
  - 用户注册表单(用户名/密码/昵称)
  - 已登录用户显示个人信息
  - 提供登出功能
- 技术要点：
  - 使用 JSP + JSTL 渲染页面
  - 包含 navbar 和 footer 公共组件
  - 根据登录状态显示不同内容
  - 表单提交处理用户认证

### 重构建议

1. 移除 JSP 改用现代模板引擎(如 Thymeleaf)
2. 将业务逻辑从页面中分离
3. 使用 Spring Security 处理认证
4. 添加密码强度验证
5. 优化错误提示方式

### 新项目实现建议

在 Spring Boot + Thymeleaf 项目中：

1. 创建 login.html 和 register.html 使用 Thymeleaf 模板
2. 使用 Fragment 复用公共组件
3. 通过 SecurityConfig 配置认证流程
4. 使用 Spring Security 处理登录/登出
5. 添加密码重置功能
6. 使用 Bootstrap 优化表单布局

## 页面 - order_back.jsp (old/src/main/webapp/order_back.jsp)

### 功能实现

- 订单管理页面，主要功能：
  - 显示用户订单列表
  - 分页显示订单
  - 每个订单可展开查看详情(商品列表)
  - 显示订单号、创建时间、总金额
- 技术要点：
  - 使用 JSP + JSTL 渲染页面
  - 包含公共组件(head, bg, navbar, footer)
  - 分页导航实现
  - 订单详情展开/收起功能

### 重构建议

1. 移除 JSP 改用现代模板引擎(如 Thymeleaf)
2. 将业务逻辑从页面中分离
3. 使用 AJAX 实现分页加载
4. 添加订单状态管理功能
5. 优化订单详情展示

### 新项目实现建议

在 Spring Boot + Thymeleaf 项目中：

1. 创建 order.html 使用 Thymeleaf 模板
2. 使用 Fragment 复用公共组件
3. 通过 OrderController 提供数据
4. 使用 AJAX 实现分页加载
5. 添加订单状态筛选功能
6. 使用 Bootstrap 优化布局

## 公共组件

### navbar.jsp (old/src/main/webapp/cpn/navbar.jsp)

- 功能：导航栏组件
- 特点：
  - 包含logo、返回按钮、主页按钮
  - 根据用户登录状态显示不同菜单项(购物车、订单)
  - 管理员显示管理入口
  - 支持多语言切换
  - 显示当前用户信息
- 重构建议：
  1. 改用Thymeleaf Fragment
  2. 集成Spring Security的认证状态
  3. 优化移动端体验

### head.jsp (old/src/main/webapp/cpn/head.jsp)

- 功能：页面头部组件
- 特点：
  - 引入main.css样式文件
  - 设置网站图标
  - 引入Font Awesome图标库
  - 引入pjax(页面无刷新加载)
  - 引入translate.js(多语言支持)
  - 引入main.js主脚本
- 重构建议：
  1. 改用Thymeleaf Fragment
  2. 优化资源加载策略
  3. 移除pjax改用现代前端路由

### footer.jsp (old/src/main/webapp/cpn/footer.jsp)

- 功能：页脚组件
- 特点：
  - 显示版权信息
  - 包含GitHub链接
  - 加载进度条
  - 引入instant.page(预加载技术)
- 重构建议：
  1. 改用Thymeleaf Fragment
  2. 添加更多联系信息
  3. 优化加载进度条实现

### bg.jsp (old/src/main/webapp/cpn/bg.jsp)

- 功能：背景动画组件
- 特点：
  - 包含10个圆形元素
  - 通过CSS实现动画效果
  - 被所有页面复用
- 重构建议：
  1. 改用Thymeleaf Fragment
  2. 优化动画性能
  3. 添加深色模式适配

## 静态资源

### main.css (old/src/main/webapp/css/main.css)

- 功能：全局样式文件
- 特点：
  - 全局样式重置和基础设置
  - 导航栏、页脚样式
  - 内容容器样式
  - 表单元素样式
  - 加载动画
  - 背景动画效果
  - 深色模式支持
  - 移动端适配
- 重构建议：
  1. 改用SASS/LESS预处理
  2. 模块化CSS结构
  3. 优化动画性能

### main.js (old/src/main/webapp/js/main.js)

- 功能：前端主JavaScript文件
- 特点：
  - 使用IIFE避免全局污染
  - 实现了加载动画控制
  - 集成PJAX实现无刷新页面导航
  - 集成translate.js实现多语言支持
  - 处理PJAX相关事件
- 重构建议：
  1. 改用模块化JavaScript
  2. 移除PJAX改用现代前端路由
  3. 优化多语言实现

## 配置文件

### web.xml (old/src/main/webapp/WEB-INF/web.xml)

- 功能：Java Web应用配置文件
- 特点：
  - 基本配置，版本为Jakarta EE 9+
  - 设置index为欢迎页面
  - 没有配置Servlet和Filter
- 重构建议：
  1. 在Spring Boot中移除web.xml
  2. 使用Java配置替代
  3. 添加必要的安全配置
