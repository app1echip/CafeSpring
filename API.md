# CafeSpring

CafeSpring API 文档

## 未登录

未登录状态可以注册账户以及验证登录

### POST /register

注册普通用户帐号

#### 请求

提供用户名和密码，可选邮箱和手机。

示例一，`id`自动生成，缺失属性为空：

```json
{
    "username": "abc",
    "password": "pas"
}
```

示例二，提供所有信息：

```json
{
    "username": "abc",
    "password": "pas",
    "email": "a@b.c",
    "phone": "1234578"
}
```

#### 响应

注册成功则自动登录并返回Token，和`/authenticate`相同；

失败则返回状态`409`和自定义消息。

### POST /authenticate

登录验证，普通用户和管理员共用，后端服务器根据JWT用户名判断角色。

#### 请求

提供用户名和密码。

示例：

```json
{
    "username": "abc",
    "password": "pas"
}
```

#### 响应

成功则返回JWT。

示例：

```
eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyX2EiLCJleHAiOjE1OTQ5NzYzMDksImlhdCI6MTU5NDk3MjcwOX0.9q7Sq682YRlIUxHtxU22V7QgJncMRgvrdgxHxwxJiF3388wp331h3L4jpBSq75NrygRt2yGY0qg-FLreAB-9WA
```

失败则返回状态`401`和自定义消息。

登录成功后则之后每次请求需要在header中附带Token：

例如`GET /profile`获得个人信息，其header中需要添加：

```
Authorization: Cafe eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyX2EiLCJleHAiOjE1OTQ5NzYzMDksImlhdCI6MTU5NDk3MjcwOX0.9q7Sq682YRlIUxHtxU22V7QgJncMRgvrdgxHxwxJiF3388wp331h3L4jpBSq75NrygRt2yGY0qg-FLreAB-9WA
```

请注意没有换行，Token前面要加一个`Cafe `（有一个空格）共五个字符。

登录后多数操作不需要再额外指出自己是谁，Token解密后就包含了用户名。

## 用户登录

经过JWT验证，之后附带Token的请求是具有权限的。

### GET /profile

获得自己的个人信息，注意由于Token可以验证身份故不需要指出用户名。

#### 响应

示例：

```json
{
    "id": "96b302b8-3677-4056-a595-97af650a4e73",
    "username": "abc",
    "password": "pas",
    "email": "a@b.c",
    "phone": null
}
```

### POST /profile/update

更系个人信息，注意空值也会覆盖原值，若一个属性不变则需要在请求中照抄。

#### 请求

类似`GET /profile`的响应。

示例一，修改上例的密码：

```json
{
    "id": "96b302b8-3677-4056-a595-97af650a4e73",
    "username": "abc",
    "password": "supersecretpasswordislong",
    "email": "a@b.c",
    "phone": null
}
```

示例二，删除邮箱（空值覆盖的体现）：

```json
{
    "id": "96b302b8-3677-4056-a595-97af650a4e73",
    "username": "abc",
    "password": "supersecretpasswordislong",
    "email": null,
    "phone": null
}
```

示例三，同上，缺失字段即为空值：

```json
{
    "id": "96b302b8-3677-4056-a595-97af650a4e73",
    "username": "abc",
    "password": "supersecretpasswordislong",
    "phone": null
}
```

#### 响应

成功返回消息`"success"`，失败返回状态`500`和错误消息。

### GET /menu

获取菜单。

#### 响应

示例：

```json
[
    {
        "id" : "3c4f5b9e-fa5b-4366-8d96-c586253c436b",
        "name" : "拿铁咖啡",
        "category" : "咖啡",
        "price" : 16.0,
        "sales" : null,
        "desc" : null,
        "img" : null
    },
    {
        "id" : "42c19e3a-7ebe-4e39-9455-396c12b558c8",
        "name" : "西瓜汁",
        "category" : "果汁",
        "price" : 14.0,
        "sales" : null,
        "desc" : null,
        "img" : null
    },
    {
        "id" : "4bd57c99-f316-4067-93dd-950ee41a7acd",
        "name" : "苹果汁",
        "category" : "果汁",
        "price" : 15.0,
        "sales" : null,
        "desc" : null,
        "img" : null
    },
    {
        "id" : "5745bb1c-ed5b-40b9-8ac4-3d4a5a45e76f",
        "name" : "鸡翅",
        "category" : "小吃",
        "price" : 10.0,
        "sales" : null,
        "desc" : null,
        "img" : null
        }
]
```

请注意为了用户端方便显示，其中`category`字段被替换为了类别名称，而在数据库实体和管理员API中`category`实际上是类别ID。\

其中：

- `name`, `category` - 字符串，餐点名称和餐点类别。
- `price`, `sales` - 浮点数和整数，代表单价和销量。
- `desc`, `img` - 字符串，前者为餐点的文本描述，后者是图片的URL（可以利用公共图床）。

### GET /order

获取历史订单。

#### 响应

示例：

```json
[
    {
        "id" : "09b5e6da-c201-4b7d-b3a3-fc533f4fb770",
        "content" : {
            "珍珠奶茶" : 1,
            "美式咖啡" : 1
        },
        "time" : "2020-07-14T00:29:29.000+00:00"
    },
    {
        "id" : "ce1edad5-5d8b-4f70-81f8-9b16921a5727",
        "content" : {
            "拿铁咖啡" : 2,
            "摩卡咖啡" : 3
        },
        "time" : "2020-07-09T03:28:00.000+00:00"
    }
]
```

请注意为了用户段显示方便，餐点内容使用字典的方式显示（存在弊端：格式不合适，名称未必独特，有空再修改）。

### POST /order/new

创建新订单。

#### 请求

使用餐点ID和数量的字典（格式不合适，有空再修改）。

示例，一杯拿铁和两杯苹果汁：

```json
{
    "3c4f5b9e-fa5b-4366-8d96-c586253c436b": 1,
    "4bd57c99-f316-4067-93dd-950ee41a7acd": 2
}
```

#### 响应

成功则返回订单ID：

示例：

```
64510b52-b0bb-4388-8702-015e612c46b6
```

失败则返回状态`500`和自定义消息。

## 管理员登录

管理员可用所有API

> 规范和文档还待统一，暂时API，CRUD操作和对应的数据库表如下：CategoryController(category), FoodController(food), OrderController(order), OrderFishController(order_food), UserController(user) UserRoleController(user_role)
