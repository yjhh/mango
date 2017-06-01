/*
Navicat MySQL Data Transfer

Source Server         : YY
Source Server Version : 50610
Source Host           : 127.0.0.1:3306
Source Database       : mango

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2017-04-10 00:16:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `sex` char(4) NOT NULL,
  `email` varchar(50) NOT NULL,
  `describe` varchar(1000) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '飞飞', '姚俊', 'e10adc3949ba59abbe56e057f20f883e', '男', '407091038@qq.com', '没啥可描述的！');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `goods_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `goods_name` varchar(100) NOT NULL,
  `goods_num` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cart_goods` (`goods_id`),
  KEY `FK_cart_store` (`store_id`),
  KEY `FK_cart_user` (`user_id`),
  CONSTRAINT `FK_cart_goods` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_cart_store` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_cart_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='购物车表';

-- ----------------------------
-- Records of cart
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `describe` varchar(100) NOT NULL,
  `parent_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8 COMMENT='类别表';

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', '家具', '总父类', '0');
INSERT INTO `category` VALUES ('2', '卧室', '卧室', '1');
INSERT INTO `category` VALUES ('3', '客厅', '客厅', '1');
INSERT INTO `category` VALUES ('4', '餐厅', '餐厅', '1');
INSERT INTO `category` VALUES ('5', '床', '床', '2');
INSERT INTO `category` VALUES ('6', '床垫', '床垫', '2');
INSERT INTO `category` VALUES ('7', '衣柜', '衣柜', '2');
INSERT INTO `category` VALUES ('8', '沙发', '沙发', '3');
INSERT INTO `category` VALUES ('9', '茶几', '茶几', '3');
INSERT INTO `category` VALUES ('10', '电视柜', '电视柜', '3');
INSERT INTO `category` VALUES ('11', '鞋柜', '鞋柜', '3');
INSERT INTO `category` VALUES ('12', '餐桌', '餐桌', '4');
INSERT INTO `category` VALUES ('13', '餐椅', '餐椅', '4');
INSERT INTO `category` VALUES ('14', '餐边柜', '餐边柜', '4');
INSERT INTO `category` VALUES ('15', '书房', '书房', '1');
INSERT INTO `category` VALUES ('16', '书桌', '书桌', '15');
INSERT INTO `category` VALUES ('17', '书柜', '书柜', '15');
INSERT INTO `category` VALUES ('18', '儿童家具', '儿童家具', '1');
INSERT INTO `category` VALUES ('19', '儿童床', '儿童床', '18');
INSERT INTO `category` VALUES ('20', '儿童衣柜', '儿童衣柜', '18');
INSERT INTO `category` VALUES ('21', '儿童椅', '儿童椅', '18');

-- ----------------------------
-- Table structure for cservice
-- ----------------------------
DROP TABLE IF EXISTS `cservice`;
CREATE TABLE `cservice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `store_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_cservice_store_id` (`store_id`),
  CONSTRAINT `FK_cservice_store_id` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='客服表';

-- ----------------------------
-- Records of cservice
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `price` double(10,2) NOT NULL,
  `stock` bigint(20) NOT NULL,
  `describe` varchar(2000) NOT NULL,
  `time_on_shelves` date NOT NULL,
  `store_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_goods_store_id` (`store_id`),
  KEY `FK_goods_category_id` (`category_id`),
  CONSTRAINT `FK_goods_category_id` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_goods_store_id` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('28', '唯美床垫', '8888.00', '-1', '唯美床垫', '2017-04-09', '3', '6');
INSERT INTO `goods` VALUES ('29', '唯美床垫', '8569.00', '0', '唯美床垫', '2017-04-09', '3', '6');
INSERT INTO `goods` VALUES ('30', '唯美床垫', '9869.00', '156', '唯美床垫', '2017-04-09', '3', '6');

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) NOT NULL,
  `goods_price` double(10,2) NOT NULL,
  `goods_num` int(11) NOT NULL,
  PRIMARY KEY (`order_id`,`goods_id`),
  KEY `FK_item_goods_id` (`goods_id`),
  CONSTRAINT `FK_item_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_item_order_id` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='订单子表';

-- ----------------------------
-- Records of item
-- ----------------------------

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderTime` date NOT NULL,
  `totalPrice` double(10,2) NOT NULL,
  `recive_name` varchar(50) NOT NULL,
  `full_address` varchar(200) NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  `store_id` int(11) NOT NULL,
  `status_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_order_status_id` (`status_id`),
  KEY `FK_order_store` (`store_id`),
  KEY `FK_order_user_id` (`user_id`),
  CONSTRAINT `FK_order_status_id` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_order_store` FOREIGN KEY (`store_id`) REFERENCES `store` (`id`) ON DELETE CASCADE,
  CONSTRAINT `FK_order_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=788491623 DEFAULT CHARSET=utf8 COMMENT='订单主表';

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(2000) NOT NULL,
  `goods_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_picture_goods_id` (`goods_id`),
  CONSTRAINT `FK_picture_goods_id` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='图片表';

-- ----------------------------
-- Records of picture
-- ----------------------------
INSERT INTO `picture` VALUES ('28', '833f52ee-507c-4127-afb6-24ff24b7a600.jpg', '28');
INSERT INTO `picture` VALUES ('29', '4933fe39-b525-446d-8339-3b21bd7d7a3e.jpg', '28');
INSERT INTO `picture` VALUES ('30', 'c94631c3-7712-4767-97a5-91942d6666bd.jpg', '29');
INSERT INTO `picture` VALUES ('31', 'd657adf0-3141-4a51-a7a6-8aef93d2b5d5.jpg', '30');

-- ----------------------------
-- Table structure for receiveinfo
-- ----------------------------
DROP TABLE IF EXISTS `receiveinfo`;
CREATE TABLE `receiveinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `telNum` varchar(50) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_receiveinfo_user_id` (`user_id`),
  CONSTRAINT `FK_receiveinfo_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='收货信息表';

-- ----------------------------
-- Records of receiveinfo
-- ----------------------------
INSERT INTO `receiveinfo` VALUES ('5', 'YY', '上海市张江镇', '15061950052', '11');

-- ----------------------------
-- Table structure for status
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='订单状态表';

-- ----------------------------
-- Records of status
-- ----------------------------
INSERT INTO `status` VALUES ('1', '未支付');
INSERT INTO `status` VALUES ('2', '已支付');
INSERT INTO `status` VALUES ('3', '确认订单，交易成功');
INSERT INTO `status` VALUES ('4', '已隐藏（用户删除）');
INSERT INTO `status` VALUES ('5', '已取消');

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(1000) NOT NULL,
  `address` varchar(1000) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telNum` varchar(50) NOT NULL,
  `describe` varchar(1000) DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_store_user` (`user_id`),
  CONSTRAINT `FK_store_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商铺表';

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES ('3', '依依家具', '上海市张江镇', '1175912454@qq.com', '15061950052', '依依家具专营家具永平', '0', '11');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginName` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `sex` varchar(4) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telNum` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `register_time` date NOT NULL,
  `icon` varchar(100) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  `info` varchar(1000) DEFAULT '该用户很懒！',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户表，包含商城用户基本信息';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('11', 'first1', 'first1', '4949ff22ac84c285545cd95885d0f475', '女', '还没有邮箱哦', '15061950052', 'null', '2017-04-09', '还没有头像哦', '0', '这个人很懒，什么都没写');
