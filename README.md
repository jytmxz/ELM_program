# ELM_program
# 饿了么点餐系统（ELM）

一个前后端分离的外卖点餐系统：**Vue 3** 前端 + **Spring Boot / MyBatis** 后端 + **MySQL** 数据库。用户可以浏览美食分类和商家、把菜品加入购物车、生成订单并支付。

## 技术栈

| 层 | 技术 |
|---|---|
| 前端 | Vue 3、Vue Router 4（Hash 模式）、axios、qs、Font Awesome 4.7、Vue CLI 5 |
| 后端 | Spring Boot 2.7.x、Spring MVC、MyBatis（mybatis-spring-boot-starter 2.3.1）、Druid 连接池 |
| 数据库 | MySQL 8.x |
| 构建 | Maven（后端）、npm（前端） |
| 运行环境 | JDK 8+、Node.js 16+（建议） |

## 系统架构

```
┌──────────────────────┐   HTTP (axios)    ┌──────────────────────────┐   JDBC (MyBatis)   ┌─────────┐
│  Vue 3 前端            │ ───────────────▶ │  Spring Boot 后端         │ ────────────────▶ │  MySQL  │
│  localhost:8081      │ ◀─────────────── │  localhost:8090          │ ◀──────────────── │  elm 库  │
└──────────────────────┘   JSON Result     └──────────────────────────┘                    └─────────┘
```

后端分层：`Controller → Service → Mapper（XML）`，实体类位于 `pojo` 包。

所有接口统一返回如下格式：

```json
{ "code": 200, "message": "成功", "data": ... }
```

失败时 `code` 为 `400`。

### 页面流程

```
首页 ─▶ 商家列表 ─▶ 商家详情（加入购物车）─▶ 确认订单 ─▶ 在线支付 ─▶ 我的订单
        登录 / 注册
```

## 项目结构

建议将三部分放在同一个仓库中：

```
elm/
├── README.md
├── sql/
│   └── elm.sql                  # 建表与示例数据（见下文「数据库设计」）
├── ELM-springboot/              # 后端
│   ├── pom.xml
│   └── src/main/
│       ├── java/com/neuedu/
│       │   ├── ElmSpringbootApplication.java
│       │   └── elm/
│       │       ├── controller/  # UserController、OrderTypeController、BusinessController、
│       │       │                #   FoodController、CartController
│       │       ├── service/     # 接口 + impl/
│       │       ├── mapper/      # MyBatis Mapper 接口
│       │       ├── pojo/        # User、Business、Food、Cart、OrderType、Orders、
│       │       │                #   OrderDetailet、DeliveryAddress
│       │       └── util/Result.java
│       └── resources/
│           ├── application.yml
│           └── mappers/*.xml
└── elm-program/                 # 前端
    ├── package.json
    ├── vue.config.js
    └── src/
        ├── main.js              # 入口 + 路由守卫
        ├── App.vue              # axios 基础地址
        ├── common.js            # sessionStorage / localStorage 封装、日期工具
        ├── router/index.js
        ├── components/          # head.vue、foot.vue
        ├── views/               # Home、BusinessList、BusinessInfo、Login、Regester、
        │                        #   Order、Payment、OrderList
        └── assets/              # css、img
```

## 数据库设计

仓库中原本没有建表脚本。下面的表结构是根据后端实体类和 Mapper 中的 SQL **反推**出来的，字段类型为建议值，可按实际需要调整。

> **注意：** `application.yml` 没有开启下划线转驼峰（`map-underscore-to-camel-case`），因此**列名必须与实体类属性名一致（驼峰命名）**，例如 `userId`、`businessId`，不能写成 `user_id`。

将以下内容保存为 `sql/elm.sql` 并执行：

```sql
CREATE DATABASE IF NOT EXISTS elm DEFAULT CHARACTER SET utf8mb4;
USE elm;

-- 用户（userId 即手机号，登录账号）
CREATE TABLE `user` (
    id        INT PRIMARY KEY AUTO_INCREMENT,
    userId    VARCHAR(20)  NOT NULL UNIQUE,
    password  VARCHAR(100) NOT NULL,
    userName  VARCHAR(50),
    userSex   INT COMMENT '1 男，0 女',
    userImg   MEDIUMTEXT,
    delTag    INT DEFAULT 1 COMMENT '逻辑删除标记，含义以实际约定为准'
);

-- 收货地址
CREATE TABLE deliveryaddress (
    daId        INT PRIMARY KEY AUTO_INCREMENT,
    contactName VARCHAR(50),
    contactSex  INT,
    contactTel  VARCHAR(20),
    address     VARCHAR(200),
    userId      VARCHAR(20)
);

-- 美食分类
CREATE TABLE ordertype (
    orderTypeId   INT PRIMARY KEY AUTO_INCREMENT,
    orderTypeImg  MEDIUMTEXT,
    orderTypeName VARCHAR(50)
);

-- 商家
CREATE TABLE business (
    businessId      INT PRIMARY KEY AUTO_INCREMENT,
    businessName    VARCHAR(100) NOT NULL,
    businessAddress VARCHAR(200),
    businessExplain VARCHAR(200),
    businessImg     MEDIUMTEXT,
    orderTypeId     INT,
    starPrice       DOUBLE COMMENT '起送价',
    deliveryPrice   DOUBLE COMMENT '配送费',
    remarks         VARCHAR(200),
    FOREIGN KEY (orderTypeId) REFERENCES ordertype(orderTypeId)
);

-- 菜品
CREATE TABLE food (
    foodId      INT PRIMARY KEY AUTO_INCREMENT,
    foodName    VARCHAR(100) NOT NULL,
    foodExplain VARCHAR(200),
    foodImg     MEDIUMTEXT,
    foodPrice   DOUBLE,
    businessId  INT NOT NULL,
    remarks     VARCHAR(200),
    FOREIGN KEY (businessId) REFERENCES business(businessId)
);

-- 购物车
CREATE TABLE cart (
    cartId     INT PRIMARY KEY AUTO_INCREMENT,
    foodId     INT NOT NULL,
    businessId INT NOT NULL,
    userId     VARCHAR(20) NOT NULL,
    quantity   INT NOT NULL,
    FOREIGN KEY (foodId) REFERENCES food(foodId),
    FOREIGN KEY (businessId) REFERENCES business(businessId)
);

-- 订单（orderState：0 未支付，1 已支付）
CREATE TABLE orders (
    orderId    INT PRIMARY KEY AUTO_INCREMENT,
    userId     VARCHAR(20) NOT NULL,
    businessId INT NOT NULL,
    orderDate  VARCHAR(20),
    orderTotal DOUBLE,
    daId       INT,
    orderState INT DEFAULT 0,
    FOREIGN KEY (businessId) REFERENCES business(businessId)
);

-- 订单明细
CREATE TABLE orderdetailet (
    odId     INT PRIMARY KEY AUTO_INCREMENT,
    orderId  INT NOT NULL,
    foodId   INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (orderId) REFERENCES orders(orderId),
    FOREIGN KEY (foodId) REFERENCES food(foodId)
);
```

### 示例数据（用于联调）

```sql
INSERT INTO ordertype (orderTypeName) VALUES ('美食'), ('早餐'), ('跑腿代购');
INSERT INTO business (businessName, businessAddress, businessExplain, orderTypeId, starPrice, deliveryPrice)
VALUES ('万家饺子（软件园E18店）', '沈阳市浑南区', '各种饺子', 1, 15, 3);
INSERT INTO food (foodName, foodExplain, foodPrice, businessId)
VALUES ('纯肉鲜肉（水饺）', '新鲜猪肉馅', 15, 1), ('玉米鲜肉（水饺）', '玉米加鲜肉', 16, 1);
INSERT INTO `user` (userId, password, userName, userSex, delTag)
VALUES ('13800000000', '123456', '测试用户', 1, 1);
```

图片字段（`*Img`）为空时页面上图片不会显示。从前端代码的注释看，图片可能是以 Base64 字符串存入数据库的，如需展示请自行准备图片数据。

### 表关系

```
ordertype 1──N business 1──N food
                   │          │
user 1──N cart ────┴──────────┘
user 1──N orders 1──N orderdetailet N──1 food
user 1──N deliveryaddress
```

## 接口文档

- 后端基础地址：`http://localhost:8090`
- 所有接口使用 `@RequestMapping`，GET 和 POST 均可调用；前端以 `application/x-www-form-urlencoded` 格式提交（`qs.stringify`）。

### 已实现

| 接口 | 参数 | 说明 |
|---|---|---|
| `/OrderTypeController/findAll` | 无 | 查询所有美食分类 |
| `/BusinessController/findBusinessByOrderTypeId` | `orderTypeId` | 查询某分类下的商家 |
| `/BusinessController/findBusinessById` | `businessId` | 查询商家详情 |
| `/FoodController/findFoodByBusinessId` | `businessId` | 查询商家的菜品 |
| `/CartController/findCartByUserIdAndBusinessId` | `userId`, `businessId` | 查询用户在某商家的购物车 |
| `/CartController/findCartSumByUserIdGroupBusinessId` | `userId` | 按商家汇总购物车数量（有 bug） |
| `/UserController/login` | `userId`, `password` | 登录，成功时 `data` 为用户对象 |

示例：

```
GET http://localhost:8090/FoodController/findFoodByBusinessId?businessId=1
```

### 前端已调用、后端待实现

| 接口 | 参数 | 用途 |
|---|---|---|
| `/UserController/saveUser` | `userId`, `password`, `userName`, `userSex` | 注册；成功时 `data` 为大于 0 的数 |
| `/CartController/saveCart` | `foodId`, `businessId`, `userId`, `quantity` | 加入购物车 |
| `/CartController/updateCart` | 同上 | 修改购物车数量 |
| `/CartController/deleteCart` | 同上 | 从购物车删除 |
| `/CartController/findList` | `userId`, `businessId` | 查询购物车，每项需带 `food` 和 `business` 对象 |
| `/OrdersController/saveOrders` | `userId`, `businessId`, `orderTotal`, `curDate` | 创建订单，`data` 返回订单 ID |
| `/OrdersController/findOrderById` | `orderId` | 订单详情，需包含 `business`、`orderTotal`、`foodList`（每项含 `quantity`） |
| `/OrdersController/findOrderByUserId` | `userId` | 用户的订单列表，每项含 `business`、`orderTotal`、`orderState` |

## 快速开始

### 环境要求

- JDK 8 或以上
- Maven 3.6+
- Node.js 16+ 与 npm
- MySQL 8.x

### 1. 初始化数据库

执行上文的建表脚本，创建数据库 `elm`。

### 2. 启动后端

编辑 `ELM-springboot/src/main/resources/application.yml`，确认数据库账号密码：

```yaml
spring:
  datasource:
    username: root
    password: 你的密码
    url: jdbc:mysql://localhost:3306/elm?serverTimezone=Asia/Shanghai
server:
  port: 8090
```

启动：

```bash
cd ELM-springboot
./mvnw spring-boot:run      
```

> `pom.xml` 中的父工程版本是 `2.7.15-SNAPSHOT`，快照版本可能无法下载。如果构建失败，请将其改为 `2.7.18`。

### 3. 修改前端接口地址

```js
axios.defaults.baseURL = 'http://localhost:8090/';
```

### 4. 启动前端

```bash
cd elm-program
npm install
npm run serve
```

浏览器访问 `http://localhost:8081`。

### 构建

```bash
# 后端
cd ELM-springboot && ./mvnw clean package

# 前端
cd elm-program && npm run build
```

