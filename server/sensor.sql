/*
Navicat MySQL Data Transfer

Source Server         : MyConnection
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : sensor

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-07-05 11:22:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sensor
-- ----------------------------
DROP TABLE IF EXISTS `sensor`;
CREATE TABLE `sensor` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `round` int(3) NOT NULL,
  `ax` decimal(5,2) NOT NULL,
  `ay` decimal(5,2) NOT NULL,
  `az` decimal(5,2) NOT NULL,
  `acceleration` decimal(5,2) NOT NULL,
  `time` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26333 DEFAULT CHARSET=utf8;
