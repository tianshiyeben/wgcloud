/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : dats

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-01-03 09:38:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_info
-- ----------------------------
DROP TABLE IF EXISTS `app_info`;
CREATE TABLE `app_info` (
  `ID` char(32) NOT NULL,
  `ACCOUNT_ID` char(32) DEFAULT NULL,
  `ACCOUNT` char(30) DEFAULT NULL,
  `HOST_NAME` char(30) DEFAULT NULL,
  `APP_PID` char(15) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `APP_NAME` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `APP_INFO_INDEX` (`ACCOUNT_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_info
-- ----------------------------

-- ----------------------------
-- Table structure for app_state
-- ----------------------------
DROP TABLE IF EXISTS `app_state`;
CREATE TABLE `app_state` (
  `ID` char(32) NOT NULL,
  `APP_INFO_ID` char(32) DEFAULT NULL,
  `CPU_PER` char(10) DEFAULT NULL,
  `MEM_PER` char(10) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `APP_STAT_INDEX` (`APP_INFO_ID`,`CREATE_TIME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_state
-- ----------------------------

-- ----------------------------
-- Table structure for cpu_state
-- ----------------------------
DROP TABLE IF EXISTS `cpu_state`;
CREATE TABLE `cpu_state` (
  `ID` char(32) NOT NULL,
  `ACCOUNT_ID` char(32) DEFAULT NULL,
  `ACCOUNT` char(30) DEFAULT NULL,
  `HOST_NAME` char(30) DEFAULT NULL,
  `USER` char(30) DEFAULT NULL,
  `SYS` char(30) DEFAULT NULL,
  `IDLE` char(30) DEFAULT NULL,
  `IOWAIT` char(30) DEFAULT NULL,
  `IRQ` char(30) DEFAULT NULL,
  `SOFT` char(30) DEFAULT NULL,
  `DATE_STR` char(30) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `CPU_ACC_HOST_INDEX` (`ACCOUNT_ID`,`HOST_NAME`,`CREATE_TIME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cpu_state
-- ----------------------------

-- ----------------------------
-- Table structure for desk_state
-- ----------------------------
DROP TABLE IF EXISTS `desk_state`;
CREATE TABLE `desk_state` (
  `ID` char(32) NOT NULL,
  `ACCOUNT_ID` char(32) DEFAULT NULL,
  `ACCOUNT` char(30) DEFAULT NULL,
  `HOST_NAME` char(30) DEFAULT NULL,
  `FILE_STSTEM` char(30) DEFAULT NULL,
  `SIZE` char(30) DEFAULT NULL,
  `USED` char(30) DEFAULT NULL,
  `AVAIL` char(30) DEFAULT NULL,
  `USE_PER` char(10) DEFAULT NULL,
  `DATE_STR` char(30) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `DESK_ACC_HOST_INDEX` (`ACCOUNT_ID`,`HOST_NAME`,`CREATE_TIME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of desk_state
-- ----------------------------

-- ----------------------------
-- Table structure for diskio_state
-- ----------------------------
DROP TABLE IF EXISTS `diskio_state`;
CREATE TABLE `diskio_state` (
  `ID` char(32) COLLATE utf8_unicode_ci NOT NULL,
  `ACCOUNT_ID` char(32) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ACCOUNT` char(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `HOST_NAME` char(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RS` char(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WS` char(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `RKBS` char(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `WKBS` char(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AWAIT` char(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `AVGQUSZ` char(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `UTIL` char(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `DATE_STR` char(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of diskio_state
-- ----------------------------

-- ----------------------------
-- Table structure for intrusion_info
-- ----------------------------
DROP TABLE IF EXISTS `intrusion_info`;
CREATE TABLE `intrusion_info` (
  `ID` char(32) NOT NULL,
  `ACCOUNT_ID` char(32) DEFAULT NULL,
  `ACCOUNT` char(10) DEFAULT NULL,
  `HOST_NAME` char(30) DEFAULT NULL,
  `LSMOD` text,
  `PASSWD_INFO` varchar(100) DEFAULT NULL,
  `CRONTAB` text,
  `PROMISC` varchar(100) DEFAULT NULL,
  `RPCINFO` text,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of intrusion_info
-- ----------------------------

-- ----------------------------
-- Table structure for log_info
-- ----------------------------
DROP TABLE IF EXISTS `log_info`;
CREATE TABLE `log_info` (
  `ID` char(32) NOT NULL,
  `ACCOUNT` char(50) DEFAULT NULL,
  `HOST_NAME` char(30) DEFAULT NULL,
  `INFO_CONTENT` varchar(500) DEFAULT NULL,
  `STATE` char(1) DEFAULT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `LOG_ACCOUNT_INDEX` (`ACCOUNT`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of log_info
-- ----------------------------

-- ----------------------------
-- Table structure for mem_state
-- ----------------------------
DROP TABLE IF EXISTS `mem_state`;
CREATE TABLE `mem_state` (
  `ID` char(32) NOT NULL,
  `ACCOUNT_ID` char(32) DEFAULT NULL,
  `ACCOUNT` char(30) DEFAULT NULL,
  `HOST_NAME` char(30) DEFAULT NULL,
  `TOTAL` char(30) DEFAULT NULL,
  `USED` char(30) DEFAULT NULL,
  `FREE` char(30) DEFAULT NULL,
  `USE_PER` char(10) DEFAULT NULL,
  `DATE_STR` char(30) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `MEM_ACC_HOST_INDEX` (`ACCOUNT_ID`,`HOST_NAME`,`CREATE_TIME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mem_state
-- ----------------------------

-- ----------------------------
-- Table structure for msg_info
-- ----------------------------
DROP TABLE IF EXISTS `msg_info`;
CREATE TABLE `msg_info` (
  `ID` char(32) NOT NULL,
  `ACCEPT_INFO` char(50) DEFAULT NULL,
  `INFO_CONTENT` varchar(255) DEFAULT NULL,
  `MSG_TITLE` varchar(100) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `ACCOUNT_ID` char(32) DEFAULT NULL,
  `ACCOUNT` char(30) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `MSG_ACCOUNT_INDEX` (`ACCOUNT`) USING BTREE,
  KEY `MSG_ACCEPT_INDEX` (`ACCEPT_INFO`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_info
-- ----------------------------

-- ----------------------------
-- Table structure for netio_state
-- ----------------------------
DROP TABLE IF EXISTS `netio_state`;
CREATE TABLE `netio_state` (
  `ID` char(32) NOT NULL,
  `ACCOUNT_ID` char(32) DEFAULT NULL,
  `ACCOUNT` char(30) DEFAULT NULL,
  `HOST_NAME` char(30) DEFAULT NULL,
  `RXPCK` char(30) DEFAULT NULL,
  `TXPCK` char(30) DEFAULT NULL,
  `RXBYT` char(30) DEFAULT NULL,
  `TXBYT` char(30) DEFAULT NULL,
  `RXCMP` char(30) DEFAULT NULL,
  `TXCMP` char(30) DEFAULT NULL,
  `RXMCST` char(30) DEFAULT NULL,
  `DATE_STR` char(30) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `NETIO_ACC_HOST_INDEX` (`ACCOUNT_ID`,`HOST_NAME`,`CREATE_TIME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of netio_state
-- ----------------------------

-- ----------------------------
-- Table structure for system_info
-- ----------------------------
DROP TABLE IF EXISTS `system_info`;
CREATE TABLE `system_info` (
  `ID` char(32) NOT NULL,
  `ACCOUNT_ID` char(32) DEFAULT NULL,
  `ACCOUNT` char(30) DEFAULT NULL,
  `HOST_NAME` char(30) DEFAULT NULL,
  `VERSION` char(100) DEFAULT NULL,
  `VERSION_DETAIL` char(200) DEFAULT NULL,
  `CPU_NUM` char(30) DEFAULT NULL,
  `YX_DAYS` char(10) DEFAULT NULL,
  `CPU_CORE_NUM` char(10) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  `CPU_XH` char(150) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of system_info
-- ----------------------------

-- ----------------------------
-- Table structure for sys_load_state
-- ----------------------------
DROP TABLE IF EXISTS `sys_load_state`;
CREATE TABLE `sys_load_state` (
  `ID` char(32) NOT NULL,
  `ACCOUNT_ID` char(32) DEFAULT NULL,
  `ACCOUNT` char(30) DEFAULT NULL,
  `HOST_NAME` char(30) DEFAULT NULL,
  `ONE_LOAD` char(30) DEFAULT NULL,
  `FIVE_LOAD` char(30) DEFAULT NULL,
  `FIFTEEN_LOAD` char(30) DEFAULT NULL,
  `USERS` char(10) DEFAULT NULL,
  `DATE_STR` char(30) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `LOAD_ACC_HOST_INDEX` (`ACCOUNT_ID`,`HOST_NAME`,`CREATE_TIME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_load_state
-- ----------------------------

-- ----------------------------
-- Table structure for tcp_state
-- ----------------------------
DROP TABLE IF EXISTS `tcp_state`;
CREATE TABLE `tcp_state` (
  `ID` char(32) NOT NULL,
  `ACCOUNT_ID` char(32) DEFAULT NULL,
  `ACCOUNT` char(30) DEFAULT NULL,
  `HOST_NAME` char(30) DEFAULT NULL,
  `ACTIVE` char(30) DEFAULT NULL,
  `PASSIVE` char(30) DEFAULT NULL,
  `RETRANS` char(30) DEFAULT NULL,
  `DATE_STR` char(30) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `TCP_ACC_HOST_INDEX` (`ACCOUNT_ID`,`HOST_NAME`,`CREATE_TIME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tcp_state
-- ----------------------------
