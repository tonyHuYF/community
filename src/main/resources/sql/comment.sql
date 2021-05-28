/*
 Navicat Premium Data Transfer

 Source Server         : mysql--集团合同系统--测试库
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 192.168.1.215:3306
 Source Schema         : community

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 28/05/2021 10:52:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'ID',
  `parent_id` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '父类id',
  `type` tinyint(4) NULL DEFAULT 1 COMMENT '类别，1：1级评论，2：2级评论',
  `commentator` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '评论人id',
  `like_count` int(4) NULL DEFAULT 0 COMMENT '点赞数',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '评论',
  `comment_count` int(4) NULL DEFAULT 0 COMMENT '评论数',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '评论表' ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
