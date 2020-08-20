# CafeSpring

CafeSpring API 文档

## 未登录

未登录状态可以注册账户以及验证登录

### POST /pub/register

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

注册成功则自动登录并返回Token，和`/pub/authenticate`相同；

失败则返回状态`409`和自定义消息。

### POST /pub/authenticate

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

成功则返回JWT、过期时间和角色。

示例：

```json
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyX2EiLCJleHAiOjE1OTQ5NzYzMDksImlhdCI6MTU5NDk3MjcwOX0.9q7Sq682YRlIUxHtxU22V7QgJncMRgvrdgxHxwxJiF3388wp331h3L4jpBSq75NrygRt2yGY0qg-FLreAB-9WA",
    "expire": "2020-07-09T03:28:00.000+00:00",
    "role": "ROLE_USER"
}
```

失败则返回状态`401`和自定义消息。

登录成功后则之后每次请求需要在header中附带Token：

例如`GET /api/profile`获得个人信息，其header中需要添加：

```text
Authorization: eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyX2EiLCJleHAiOjE1OTQ5NzYzMDksImlhdCI6MTU5NDk3MjcwOX0.9q7Sq682YRlIUxHtxU22V7QgJncMRgvrdgxHxwxJiF3388wp331h3L4jpBSq75NrygRt2yGY0qg-FLreAB-9WA
```

登录后多数操作不需要再额外指出自己是谁，Token解密后就包含了用户名。

### GET /pub/menu

获取菜单。

#### 响应

示例：

```json
[
    {
        "id" : "3c4f5b9e-fa5b-4366-8d96-c586253c436b",
        "name" : "拿铁咖啡",
        "cate" : "咖啡",
        "price" : 16.0,
        "stock" : 53,
        "sales" : 34,
        "des" : "意大利浓缩咖啡与牛奶的经典混合",
        "img" : null
    },
    {
        "id" : "42c19e3a-7ebe-4e39-9455-396c12b558c8",
        "name" : "西瓜汁",
        "cate" : "果汁",
        "price" : 14.0,
        "stock" : 43,
        "sales" : 21,
        "des" : "口味清甜细腻",
        "img" : null
    },
    {
        "id" : "4bd57c99-f316-4067-93dd-950ee41a7acd",
        "name" : "苹果汁",
        "cate" : "果汁",
        "price" : 15.0,
        "stock" : 67,
        "sales" : 69,
        "des" : "app1e juice",
        "img" : null
    },
    {
        "id" : "5745bb1c-ed5b-40b9-8ac4-3d4a5a45e76f",
        "name" : "鸡翅",
        "cate" : "小吃",
        "price" : 10.0,
        "stock" : 45,
        "sales" : 21,
        "des" : "上等鸡翅，以椒盐淬之，色泽金黄、椒盐清香、咸香酥嫩。",
        "img" : null
    }
]
```

其中：

- `id` - UUID
- `name` - 名称，无限制
- `cate` - 类型，无限制
- `price` - 售价，小数
- `stock` - 库存，随订单自动减少
- `sales` - 销量，随订单自动增加
- `des` - 描述
- `img` - 图像URL

## 用户登录

经过JWT验证，之后附带Token的请求是具有权限的。

### GET /api/profile

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

### POST /api/profile/update

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

`id`会被忽略，后端实际上根据JWT确定用户。

#### 响应

成功返回消息`"Succeed"`，失败返回状态`500`和错误消息。

### GET /api/order

获取历史订单。

#### 响应

示例：

```json
[
    {
        "id" : "09b5e6da-c201-4b7d-b3a3-fc533f4fb770",
        "content" : [
            {
                "id" : "3c4f5b9e-fa5b-4366-8d96-c586253c436b",
                "qty" : 1
            },
            {
                "id" : "5745bb1c-ed5b-40b9-8ac4-3d4a5a45e76f",
                "qty" : 2
            }
        ],
        "time" : "2020-07-14T00:29:29.000+00:00"
    },
    {
        "id" : "ce1edad5-5d8b-4f70-81f8-9b16921a5727",
        "content" : [
            {
                "id" : "42c19e3a-7ebe-4e39-9455-396c12b558c8",
                "qty" : 10
            }
        ],
        "time" : "2020-07-09T03:28:00.000+00:00"
    }
]
```

`id`为订单ID，而`content.id`为餐点ID，`content.qty`为餐点数量。

### POST /api/order/new

创建新订单。

#### 请求

使用餐点ID和数量的对象。

示例，一杯拿铁和两杯苹果汁：

```json
[
    {
        "id" : "3c4f5b9e-fa5b-4366-8d96-c586253c436b",
        "qty" : 1
    },
    {
        "id" : "4bd57c99-f316-4067-93dd-950ee41a7acd",
        "qty" : 2
    }
]
```

#### 响应

成功则返回订单ID：

示例：

```text
64510b52-b0bb-4388-8702-015e612c46b6
```

失败则返回状态`500`和自定义消息。

## 管理员登录

管理员可以作为普通用户访问上述API，同时可以访问数据库管理API。  
管理员API没有专属的DTO设计，实际上是对后端hibernate的简单转发。  
为了理解和使用管理员API，需要对数据库结构有基本的了解。  
以操作餐点的API为例，典型的CRUD操作看起来像是这样的：

### /admin/food

完整的餐点信息。

#### 查询

列出所有餐点的完整信息。

```text
GET /admin/food
```

获取到的内容和`GET /pub/menu`相同，此处略去。

#### 增加

增加一个新的餐点。

```text
POST /admin/food/update
```

```json
{
    "id" : "no use",
    "name" : "新的咖啡",
    "cate" : "咖啡",
    "price" : 16.0,
    "stock" : 53,
    "des" : null
}
```

插入数据的关键在于主码不存在，只要`id`不是一个已有的餐点ID，实际上可以填入任何字符、null或者干脆省略，数据库会自动生成UUID。  
其它没有的字段也会被存为null。

#### 修改

修改一个已有的餐点。

```text
POST /admin/food/update
```

```json
{
    "id" : "5745bb1c-ed5b-40b9-8ac4-3d4a5a45e76f",
    "name" : "鸡翅",
    "cate" : "小吃",
    "price" : 19.0,
    "stock" : 45,
    "sales" : 21,
    "des" : "上等鸡翅，以椒盐淬之，色泽金黄、椒盐清香、咸香酥嫩。",
    "img" : null
}
```

注意到修改和增加使用的是同一个API，修改的关键在于主码存在（即修改一个已有的餐点，不存在的ID就成为上述增加的操作）。  
和`PUT /put/profile`更新的行为相似的是，丢掉的字段会被覆盖为null，比如只更新售价则需要带上其它字段防止被置为空。

#### 删除

删除一个已有的餐点。

```text
DELETE /admin/food/delete
```

```json
{
    "id" : "5745bb1c-ed5b-40b9-8ac4-3d4a5a45e76f"
}
```

实际上后端可以接受的格式和`POST /admin/food/update`相同，但附加除了`id`之外的其它字段显然是没有意义的。  

所有增、删和改的操作如果发生错误，则返回状态`500`。

其他表的格式如下：

### /admin/user

所有的用户信息，增删该查操作和上述操作类似。

```json
{
    "id" : "2f729779-d174-44d5-b74a-5e0797f50f92",
    "username" : "user_c",
    "email" : null,
    "password" : "pass_c",
    "phone" : null
}
```

### /admin/user_role

用户角色表，只需要查询和修改。

```json
{
    "user" : "2f729779-d174-44d5-b74a-5e0797f50f92",
    "role" : "ROLE_USER"
}
```

`role`可选`ROLE_USER`或者`ROLE_ADMIN`，`user`为用户ID。

### /admin/ordre

订单表（与用户专属API不同，不含订单餐点内容）。

```json
{
    "id" : "09b5e6da-c201-4b7d-b3a3-fc533f4fb770",
    "time" : "2020-07-14T00:29:29.000+00:00",
    "user" : "468e78f3-0532-4a10-99d5-4cddf7618b11"
}
```

注意在管理API和数据库中订单叫做"ordre"而不是"order"。  
`id`为订单ID，`time`为订单时间，`user`为用户ID。

### /admin/ordre_food

订单内容表。

```json
{
    "id" : {
        "ordre" : "09b5e6da-c201-4b7d-b3a3-fc533f4fb770",
        "food" : "ab12de66-67f2-472a-b2a5-7ee5df12eeb6"
    },
    "qty" : 1
}
```

主码包含两个属性，其中`id.ordre`为订单ID，`id.food`为餐点ID，`qty`为餐点数量。
