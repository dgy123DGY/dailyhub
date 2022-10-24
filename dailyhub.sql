/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : dailyhub

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2022-01-20 16:26:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for m_collect
-- ----------------------------
DROP TABLE IF EXISTS `m_collect`;
CREATE TABLE `m_collect` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `collected` date DEFAULT NULL,
  `created` datetime(6) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `personal` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6yx2mr7fgvv204y8jw5ubsn7h` (`user_id`),
  CONSTRAINT `FK6yx2mr7fgvv204y8jw5ubsn7h` FOREIGN KEY (`user_id`) REFERENCES `m_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for m_user
-- ----------------------------
DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `created` datetime(6) DEFAULT NULL,
  `lasted` datetime(6) DEFAULT NULL,
  `open_id` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
