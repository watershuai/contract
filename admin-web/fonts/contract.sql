/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : contract

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2020-05-29 18:10:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for contract
-- ----------------------------
DROP TABLE IF EXISTS `contract`;
CREATE TABLE `contract` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `wz_name` varchar(50) DEFAULT NULL,
  `sku_name` varchar(50) DEFAULT NULL,
  `measurement` varchar(50) DEFAULT NULL,
  `supplier` varchar(100) DEFAULT NULL,
  `contract_code` varchar(100) DEFAULT NULL,
  `number` int(50) DEFAULT NULL,
  `over_namber` int(50) DEFAULT NULL COMMENT '剩余数量',
  `price` varchar(50) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `price_type` int(2) DEFAULT '0' COMMENT '单价类型:0 固定单价，1浮动单价',
  `create_time` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL,
  `status` int(2) DEFAULT '1',
  `reserve1` varchar(100) DEFAULT NULL,
  `reserve2` varchar(100) DEFAULT NULL,
  `reserve3` varchar(100) DEFAULT NULL,
  `reserve4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contract
-- ----------------------------

-- ----------------------------
-- Table structure for purchase_order
-- ----------------------------
DROP TABLE IF EXISTS `purchase_order`;
CREATE TABLE `purchase_order` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `purchase_no` varchar(50) NOT NULL,
  `wz_name` varchar(50) DEFAULT NULL,
  `sku_name` varchar(50) DEFAULT NULL,
  `measurement` varchar(50) DEFAULT NULL COMMENT '物资单位/克',
  `contract_code` varchar(100) DEFAULT NULL,
  `plan_number` int(50) DEFAULT NULL,
  `reality_number` int(50) DEFAULT NULL,
  `price` varchar(50) DEFAULT NULL,
  `quality_require` varchar(100) DEFAULT NULL COMMENT '质量标准',
  `price_type` int(2) DEFAULT '0' COMMENT '单价类型:0 固定单价，1浮动单价',
  `ask_department` varchar(100) DEFAULT NULL COMMENT '申请部门',
  `build_position` varchar(100) DEFAULT NULL COMMENT '施工部位',
  `build_company` varchar(100) DEFAULT NULL COMMENT '施工单位',
  `receiver_name` varchar(100) DEFAULT NULL COMMENT '收货人',
  `arrive_addrss` varchar(100) DEFAULT NULL COMMENT '到货地点',
  `purchase_type` varchar(50) NOT NULL COMMENT '采购类别',
  `plan_time` varchar(50) DEFAULT NULL COMMENT '计划到货时间',
  `reality_time` varchar(50) DEFAULT NULL COMMENT '实际到货时间',
  `url` varchar(1000) DEFAULT NULL COMMENT '附件地址',
  `create_time` varchar(50) DEFAULT NULL,
  `update_time` varchar(50) DEFAULT NULL,
  `remake` varchar(500) DEFAULT NULL COMMENT '备注',
  `status` int(2) DEFAULT '1',
  `reserve1` varchar(100) DEFAULT NULL,
  `reserve2` varchar(100) DEFAULT NULL,
  `reserve3` varchar(100) DEFAULT NULL,
  `reserve4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchase_order
-- ----------------------------

-- ----------------------------
-- Table structure for snpon_id
-- ----------------------------
DROP TABLE IF EXISTS `snpon_id`;
CREATE TABLE `snpon_id` (
  `order_no` varchar(100) DEFAULT NULL,
  `id` int(100) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of snpon_id
-- ----------------------------
INSERT INTO `snpon_id` VALUES ('20200601', '1');

-- ----------------------------
-- Table structure for supplier
-- ----------------------------
DROP TABLE IF EXISTS `supplier`;
CREATE TABLE `supplier` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `user` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `bank` varchar(100) DEFAULT NULL,
  `account` varchar(100) DEFAULT NULL,
  `code` varchar(60) DEFAULT NULL,
  `create_time` varchar(50) DEFAULT NULL,
  `status` int(2) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of supplier
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) NOT NULL,
  `username` varchar(64) DEFAULT NULL COMMENT '账号',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `phone` varchar(64) DEFAULT NULL COMMENT '手机号',
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_time` varchar(100) DEFAULT NULL COMMENT '注册时间',
  `login_time` varchar(100) DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  `type` int(1) DEFAULT '0' COMMENT '类型',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  KEY `login_time` (`login_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户账号表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '3', '3', '3', null, null, '波波博', null, null, null, '1', '0');
