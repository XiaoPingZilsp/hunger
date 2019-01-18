/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : hungry

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2019-01-18 14:13:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_collect`
-- ----------------------------
DROP TABLE IF EXISTS `tb_collect`;
CREATE TABLE `tb_collect` (
  `collect_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏主键',
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺ID',
  `food_id` int(11) DEFAULT NULL COMMENT '食物ID',
  `collect_date` datetime(6) DEFAULT NULL COMMENT '收藏日期',
  `flag` int(11) NOT NULL COMMENT '标记收藏类型，0为收藏店铺，1为收藏食物',
  PRIMARY KEY (`collect_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_collect
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_food`
-- ----------------------------
DROP TABLE IF EXISTS `tb_food`;
CREATE TABLE `tb_food` (
  `food_id` int(11) NOT NULL AUTO_INCREMENT,
  `foodname` varchar(20) NOT NULL COMMENT '食物名',
  `shop_id` int(11) NOT NULL COMMENT '外键',
  `type_id` int(11) NOT NULL COMMENT '外键',
  `price` float(15,0) NOT NULL COMMENT '食物价格',
  `information` varchar(50) DEFAULT NULL COMMENT '食物简介',
  `picture` varchar(30) DEFAULT NULL COMMENT '食物图片',
  `recommend` varchar(15) DEFAULT NULL COMMENT '是否推荐',
  PRIMARY KEY (`food_id`),
  KEY `shop_id` (`shop_id`),
  KEY `type_id` (`type_id`),
  CONSTRAINT `shop_id` FOREIGN KEY (`shop_id`) REFERENCES `tb_shop` (`shop_id`),
  CONSTRAINT `type_id` FOREIGN KEY (`type_id`) REFERENCES `tb_type` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_food
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_shop`
-- ----------------------------
DROP TABLE IF EXISTS `tb_shop`;
CREATE TABLE `tb_shop` (
  `shop_id` int(11) NOT NULL AUTO_INCREMENT,
  `shopname` varchar(20) NOT NULL COMMENT '店铺名',
  `address` varchar(50) NOT NULL COMMENT '店铺地址',
  `phone` varchar(20) NOT NULL COMMENT '店铺电话',
  `information` varchar(50) DEFAULT NULL COMMENT '店铺介绍',
  `picture` varchar(30) DEFAULT NULL COMMENT '店铺图片',
  `comment` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_shop
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_type`
-- ----------------------------
DROP TABLE IF EXISTS `tb_type`;
CREATE TABLE `tb_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `typename` varchar(30) NOT NULL COMMENT '食物种类',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_type
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL COMMENT '账号',
  `userpass` varchar(20) NOT NULL COMMENT '密码',
  `phone` varchar(30) NOT NULL COMMENT '联系电话',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `comment` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'lhh', '11', '', null, null);
INSERT INTO `tb_user` VALUES ('2', 'hao', '123456', '', null, null);
