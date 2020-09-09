/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : spring_cloud_demo

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 09/09/2020 16:34:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for shop_address
-- ----------------------------
DROP TABLE IF EXISTS `shop_address`;
CREATE TABLE `shop_address`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(25) NULL DEFAULT NULL COMMENT '订单ID',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `first_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `postcode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fax` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `country_id` int(11) NULL DEFAULT NULL,
  `region_id` int(11) NULL DEFAULT NULL,
  `city` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_by` int(11) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_by` int(11) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单地址表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shop_address
-- ----------------------------
INSERT INTO `shop_address` VALUES (1, 1, '123456@qq.com', 'lockie', 'zou', '440300', NULL, '13500001111', 1, 1, '1', '南山区', '后海街道', 1, '2020-08-01 10:27:50', 1, '2020-09-09 10:27:57');
INSERT INTO `shop_address` VALUES (2, 2, '78979@163.com', 'jack', 'ma', '110010', NULL, '18811110000', 1, 1, '1', '北京市', '朝阳区', 1, '2020-07-01 13:48:58', 1, '2020-08-19 13:49:07');
INSERT INTO `shop_address` VALUES (10, 11, 'qwe@163.com', 'tony', 'ma', '20001', '', '13500001111', 1, 1, '1', '天津市', '高新区', 1, '2020-09-09 08:25:40', 1, '2020-09-09 08:25:40');

-- ----------------------------
-- Table structure for shop_order
-- ----------------------------
DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE `shop_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `order_no` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_date` datetime(0) NULL DEFAULT NULL,
  `order_status` int(5) NULL DEFAULT NULL,
  `order_total` decimal(11, 0) NULL DEFAULT NULL,
  `order_currency_code` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `total_qty_ordered` int(11) NULL DEFAULT NULL,
  `total_item_count` int(11) NULL DEFAULT NULL,
  `order_type` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_source` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `order_email_flag` int(5) NULL DEFAULT NULL,
  `order_unpay_email_flag` int(5) NULL DEFAULT NULL,
  `create_by` int(11) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_by` int(11) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '订单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shop_order
-- ----------------------------
INSERT INTO `shop_order` VALUES (1, 1, '201903291029001', '2019-03-29 10:29:45', 1, 150, 'CNY', 1, 1, '1', NULL, 1, NULL, 1, '2019-03-29 10:30:24', 1, '2019-03-29 10:30:30');
INSERT INTO `shop_order` VALUES (2, 1, '201903301029001', '2019-03-29 10:29:45', 1, 128, 'CNY', 1, 1, '1', NULL, 1, NULL, 1, '2019-03-29 10:30:24', 1, '2019-03-29 10:30:30');
INSERT INTO `shop_order` VALUES (8, 1, '20200909162224', '2020-09-09 08:22:25', 1, 1, '', 100, 1, '1', '2', 1, 0, 1, '2020-09-09 08:22:25', 1, '2020-09-09 08:22:25');
INSERT INTO `shop_order` VALUES (9, 1, '20200909162402', '2020-09-09 08:24:03', 1, 1, '', 100, 1, '1', '2', 1, 0, 1, '2020-09-09 08:24:03', 1, '2020-09-09 08:24:03');
INSERT INTO `shop_order` VALUES (10, 1, '20200909162449', '2020-09-09 08:24:50', 1, 1, '', 100, 1, '1', '2', 1, 0, 1, '2020-09-09 08:24:50', 1, '2020-09-09 08:24:50');
INSERT INTO `shop_order` VALUES (11, 1, '20200909162539', '2020-09-09 08:25:40', 1, 1, '', 100, 1, '1', '2', 1, 0, 1, '2020-09-09 08:25:40', 1, '2020-09-09 08:25:40');

-- ----------------------------
-- Table structure for shop_product
-- ----------------------------
DROP TABLE IF EXISTS `shop_product`;
CREATE TABLE `shop_product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `short_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_color` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `img_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `product_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sku` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `asin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `model` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `country_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `channel` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `active` int(5) NULL DEFAULT NULL,
  `create_by` int(11) NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_by` int(11) NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '产品表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of shop_product
-- ----------------------------
INSERT INTO `shop_product` VALUES (1, '小米手机', '红米Note', 'red', NULL, NULL, 'ReMi001', NULL, NULL, 'CNY', NULL, 1, 1, '2019-03-29 10:31:55', 1, '2019-03-29 10:31:59');
INSERT INTO `shop_product` VALUES (2, '华为手机', '华为Mate', 'red', NULL, NULL, 'Mate20', NULL, NULL, 'CNY', NULL, 1, 1, '2019-03-29 10:31:55', 1, '2019-03-29 10:31:59');
INSERT INTO `shop_product` VALUES (3, 'OPPO手机', 'OPPOR系列', 'red', NULL, NULL, 'R11', NULL, NULL, 'CNY', NULL, 1, 1, '2019-03-29 10:31:55', 1, '2019-03-29 10:31:59');

-- ----------------------------
-- Table structure for sys_task
-- ----------------------------
DROP TABLE IF EXISTS `sys_task`;
CREATE TABLE `sys_task`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `task_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `task_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明',
  `job_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '任务类',
  `cron_expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '规则表达式',
  `is_enable` smallint(1) NOT NULL COMMENT '是否启用,1:启用，0:停用',
  `status` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态',
  `created_time` datetime(0) NOT NULL COMMENT '创建时间',
  `updated_time` datetime(0) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统任务调度' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_task
-- ----------------------------
INSERT INTO `sys_task` VALUES (1, '测试任务1', '测试任务1, 1分钟执行一次', 'com.lockie.quartz.task.TestTask1', '0 */1 * * * ?', 0, '1', '2019-07-12 15:08:44', '2019-07-12 15:09:37');
INSERT INTO `sys_task` VALUES (2, '测试任务2', '测试任务2, 2分钟执行一次', 'com.lockie.quartz.task.TestTask2', '0 */2 * * * ?', 1, '1', '2019-07-12 15:08:44', '2019-07-12 15:09:37');
INSERT INTO `sys_task` VALUES (3, '测试任务3', '测试任务3, 3分钟执行一次', 'com.lockie.quartz.task.TestTask3', '0 */3 * * * ?', 1, '1', '2019-07-12 15:08:44', '2019-07-12 15:09:37');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(125) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `status` int(5) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other_account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `other_account_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `update_by` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `update_date` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'lockie', '123456', '13200001111', 'lockie@qq.com', 1, '广东省深圳市南山区', NULL, NULL, '1', '2019-03-29 10:28:17', '1', '2019-03-29 10:28:22');
INSERT INTO `sys_user` VALUES (2, 'jack', '123456', '13500001111', 'jack@qq.com', 1, '广东省深圳市福田区', NULL, NULL, '1', '2019-03-29 10:29:09', '1', '2019-03-29 10:29:14');

SET FOREIGN_KEY_CHECKS = 1;
