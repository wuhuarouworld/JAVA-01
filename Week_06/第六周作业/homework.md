## **第12节课作业实践**

### 基于电商交易场景（用户、商品、订单），设计一套简单的表结构，提交DDL 的 SQL 文件到 Github（后面2周的作业依然要是用到这个表结构）。

**用户表**

CREATE TABLE `test`.`t_user`  (
  `id` int(11) NOT NULL COMMENT '自增主键',
  `nickname` varchar(64) NOT NULL COMMENT '昵称',
  `password` varchar(128) NOT NULL COMMENT '密码',
  `gender` varchar(255) NOT NULL COMMENT '性别',
  `birthday` date NULL COMMENT '生日',
  `mobile` int(11) NOT NULL COMMENT '手机号',
  `create_time` datetime NOT NULL COMMENT '数据创建时间',
  `update_time` datetime NOT NULL COMMENT '数据最后更新时间',
  PRIMARY KEY (`id`)
);



**用户地址表**

CREATE TABLE `test`.`t_user_address`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `province` varchar(32) NOT NULL,
  `city` varchar(32) NOT NULL,
  `regin` varchar(32) NOT NULL,
  `detail` varchar(255) NOT NULL COMMENT '详细地址',
  `tag` varchar(16) NULL DEFAULT NULL COMMENT '标签',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
);



**商品表**

CREATE TABLE `test`.`t_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `shop_id` int(11) NOT NULL COMMENT '店铺id',
  `title` varchar(20) NOT NULL COMMENT '商品标题',
  `sub_title` varchar(64) NOT NULL COMMENT '商品子标题',
  `description` varchar(255) NOT NULL COMMENT '商品详情',
  `img_url` varchar(255) NOT NULL COMMENT '商品图片',
  `product_status` tinyint(2) NOT NULL COMMENT '商品状态1.上架，2.下架',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
);



**商品sku表**

CREATE TABLE `test`.`t_product_sku`  (
  `id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL COMMENT '商品id',
  `title` varchar(20) NOT NULL COMMENT '商品标题',
  `sub_title` varchar(64) NOT NULL COMMENT '商品子标题',
  `description` varchar(255) NOT NULL COMMENT '商品详情',
  `img_url` varchar(255) NOT NULL COMMENT '商品图片',
  `stock` int(11) NOT NULL COMMENT '商品库存',
  `sku_status` tinyint(2) NOT NULL DEFAULT 1 COMMENT 'sku状态1.上架，2.下架',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
);



**店铺表**

CREATE TABLE `test`.`t_shop`  (
  `id` int(11) NOT NULL,
  `owner_id` int(11) NOT NULL COMMENT '店主id',
  `owner_name` varchar(255) NOT NULL COMMENT '店主姓名',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `shop_name` varchar(32) NOT NULL COMMENT '店铺名称',
  `shop_status` tinyint(2) NOT NULL DEFAULT 1 COMMENT '店铺状态1.营业，2.关闭，3.审核',
  `shop_description` varchar(255) NOT NULL COMMENT '店铺描述',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
);



**订单表**

CREATE TABLE `test`.`t_order`  (
  `id` int(11) NOT NULL,
  `order_number` varchar(64) NOT NULL COMMENT '订单号',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `order_price` decimal(10, 2) NOT NULL COMMENT '订单总价',
  `user_address_id` int(11) NOT NULL COMMENT '订单收货地址id',
  `order_status` tinyint(2) NOT NULL DEFAULT 1 COMMENT '订单状态1.下单，2.待支付，3.已支付，4.待收货，5.已收货，6.已退款，7.交易完成',
  `order_pay` decimal(10, 2) NOT NULL COMMENT '订单实付',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
);