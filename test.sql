/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 02/04/2021 16:33:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for a
-- ----------------------------
DROP TABLE IF EXISTS `a`;
CREATE TABLE `a`  (
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id` int(11) NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of a
-- ----------------------------
INSERT INTO `a` VALUES ('上海', 1, 23, '李四');
INSERT INTO `a` VALUES ('北京', 2, 33, '张三');
INSERT INTO `a` VALUES ('哈哈', 3, 23, '亲戚');
INSERT INTO `a` VALUES ('深圳', 4, 85, '李四');

-- ----------------------------
-- Table structure for b
-- ----------------------------
DROP TABLE IF EXISTS `b`;
CREATE TABLE `b`  (
  `age` int(11) NULL DEFAULT NULL,
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of b
-- ----------------------------
INSERT INTO `b` VALUES (20, '男', '李四', 1);
INSERT INTO `b` VALUES (30, '女', '马六', 2);
INSERT INTO `b` VALUES (10, '男', '李四', 3);

-- ----------------------------
-- Table structure for sort
-- ----------------------------
DROP TABLE IF EXISTS `sort`;
CREATE TABLE `sort`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `parentId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sort
-- ----------------------------
INSERT INTO `sort` VALUES (1, '前端', 0);
INSERT INTO `sort` VALUES (2, 'html', 1);
INSERT INTO `sort` VALUES (3, 'vue', 1);
INSERT INTO `sort` VALUES (4, 'js', 1);
INSERT INTO `sort` VALUES (5, 'jQuery', 1);
INSERT INTO `sort` VALUES (6, '后端', 0);
INSERT INTO `sort` VALUES (7, 'java代码', 6);
INSERT INTO `sort` VALUES (8, 'ssm框架', 6);
INSERT INTO `sort` VALUES (9, 'springboot框架', 6);
INSERT INTO `sort` VALUES (10, '数据库', 0);
INSERT INTO `sort` VALUES (11, 'mysql', 10);
INSERT INTO `sort` VALUES (12, 'oracle', 10);
INSERT INTO `sort` VALUES (13, 'db2', 10);
INSERT INTO `sort` VALUES (14, 'sqlserver', 10);

-- ----------------------------
-- Table structure for tb_position
-- ----------------------------
DROP TABLE IF EXISTS `tb_position`;
CREATE TABLE `tb_position`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL,
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `createTime` datetime(6) NULL DEFAULT NULL,
  `enable` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `temp` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_position
-- ----------------------------
INSERT INTO `tb_position` VALUES (1, 1, 'java开发工程师', '2021-04-02 11:53:47.000000', '1', NULL);
INSERT INTO `tb_position` VALUES (2, 2, '项目经理', '2021-04-02 11:54:53.000000', '0', NULL);
INSERT INTO `tb_position` VALUES (3, 3, '测试', '2021-04-02 11:54:55.000000', '1', NULL);
INSERT INTO `tb_position` VALUES (4, 5, '产品工程师', '2021-04-02 11:55:19.000000', '1', NULL);
INSERT INTO `tb_position` VALUES (5, 7, '总监', '2021-04-02 11:56:19.000000', '1', NULL);
INSERT INTO `tb_position` VALUES (6, 8, '卫生员', '2021-04-02 11:56:17.000000', '1', NULL);
INSERT INTO `tb_position` VALUES (7, 9, '总裁', '2021-04-02 15:28:10.000000', '1', NULL);
INSERT INTO `tb_position` VALUES (8, 30, 'java开发', '2021-04-02 15:28:34.000000', '1', NULL);
INSERT INTO `tb_position` VALUES (9, 31, 'java开发', '2021-04-02 15:28:47.000000', '1', NULL);
INSERT INTO `tb_position` VALUES (10, 32, 'java开发', '2021-04-02 15:29:02.000000', '1', '试试');
INSERT INTO `tb_position` VALUES (11, 37, 'java开发', '2021-04-02 15:29:32.000000', '1', NULL);

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `phone` bigint(20) NULL DEFAULT NULL,
  `time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES (1, '王五', 'abc123', 13711582258, '2021-03-18 18:03:23');
INSERT INTO `tb_user` VALUES (2, '李四', '123qwe', 13711582258, '2021-03-17 00:00:00');
INSERT INTO `tb_user` VALUES (5, 'James', '123', 13711582258, '2021-03-17 00:00:00');
INSERT INTO `tb_user` VALUES (6, 'Hardan', '123', 13711582258, '2021-03-17 00:00:00');
INSERT INTO `tb_user` VALUES (7, '王五', 'abc123', 13711582258, '2021-03-18 14:47:22');
INSERT INTO `tb_user` VALUES (8, 'tom', '123', 18012346985, '2021-03-17 14:51:21');
INSERT INTO `tb_user` VALUES (30, 'txt', NULL, 13630914759, '2021-03-17 18:21:32');
INSERT INTO `tb_user` VALUES (31, 'xls', NULL, 13612341234, '2021-03-17 18:22:23');
INSERT INTO `tb_user` VALUES (32, 'xls', NULL, 13712341235, '2021-03-17 18:22:23');
INSERT INTO `tb_user` VALUES (33, 'xlsx', NULL, 13212345678, '2021-03-17 18:22:38');
INSERT INTO `tb_user` VALUES (34, 'xlsx', NULL, 13312345679, '2021-03-17 18:22:38');
INSERT INTO `tb_user` VALUES (37, '王吉1', '123', 13736982587, '2021-03-18 13:51:37');
INSERT INTO `tb_user` VALUES (38, '王吉', '123', 13736982587, '2021-03-18 14:47:22');
INSERT INTO `tb_user` VALUES (39, 'txt', NULL, 13042925810, '2021-03-18 14:47:22');
INSERT INTO `tb_user` VALUES (40, 'txt', NULL, 13650914759, '2021-03-18 14:47:22');
INSERT INTO `tb_user` VALUES (41, 'xls', NULL, 13032165498, '2021-03-18 14:50:40');
INSERT INTO `tb_user` VALUES (42, 'xls', NULL, 13232165499, '2021-03-18 14:50:40');
INSERT INTO `tb_user` VALUES (43, 'xlsx', NULL, 13212345078, '2021-03-18 14:51:01');
INSERT INTO `tb_user` VALUES (44, 'xlsx', NULL, 13312346679, '2021-03-18 14:51:01');
INSERT INTO `tb_user` VALUES (45, 'txt', NULL, 13042325810, '2021-03-19 08:43:59');
INSERT INTO `tb_user` VALUES (46, 'txt', NULL, 13650967759, '2021-03-19 08:43:59');
INSERT INTO `tb_user` VALUES (47, 'txt', NULL, 13042332110, '2021-03-19 08:48:28');
INSERT INTO `tb_user` VALUES (48, 'txt', NULL, 13656367759, '2021-03-19 08:48:28');

SET FOREIGN_KEY_CHECKS = 1;
