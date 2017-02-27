/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50527
Source Host           : localhost:3306
Source Database       : apiserver

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2016-05-11 09:26:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `api_data`
-- ----------------------------
DROP TABLE IF EXISTS `api_data`;
CREATE TABLE `api_data` (
  `key` varchar(20) NOT NULL DEFAULT '',
  `value` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`key`,`value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of api_data
-- ----------------------------

-- ----------------------------
-- Table structure for `api_detailreports`
-- ----------------------------
DROP TABLE IF EXISTS `api_detailreports`;
CREATE TABLE `api_detailreports` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `day` date NOT NULL COMMENT '执行日',
  `round` int(3) NOT NULL COMMENT '执行轮次',
  `casetype` varchar(10) DEFAULT NULL COMMENT '案例类型',
  `sequencename` varchar(20) DEFAULT NULL COMMENT '执行案例名称',
  `index` varchar(4) DEFAULT NULL COMMENT '执行顺序号',
  `apitype` varchar(10) NOT NULL COMMENT '接口类型：http,socket...........',
  `apiname` varchar(50) NOT NULL COMMENT '接口名称',
  `status` varchar(20) NOT NULL COMMENT '接口状态',
  `responsecode` varchar(50) DEFAULT NULL COMMENT '返回码',
  `message` varchar(5000) DEFAULT NULL COMMENT '返回消息',
  `starttime` datetime DEFAULT NULL COMMENT '开始时间',
  `endtime` datetime DEFAULT NULL COMMENT '结束时间',
  `exectime` varchar(10) DEFAULT NULL COMMENT '后台执行时间',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of api_detailreports
-- ----------------------------

-- ----------------------------
-- Table structure for `api_runreports`
-- ----------------------------
DROP TABLE IF EXISTS `api_runreports`;
CREATE TABLE `api_runreports` (
  `day` date NOT NULL COMMENT '执行日',
  `round` int(3) NOT NULL COMMENT '执行轮次',
  `apitotal` int(5) DEFAULT NULL COMMENT '执行的API总数',
  `success` int(5) DEFAULT NULL COMMENT '执行成功的API数',
  `fail` int(5) DEFAULT NULL COMMENT '执行失败的API数',
  `notrun` int(5) DEFAULT NULL COMMENT '未执行的API数',
  `status` int(1) NOT NULL COMMENT '本轮次执行状态',
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`day`,`round`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of api_runreports
-- ----------------------------
INSERT INTO `api_runreports` VALUES ('2016-04-29', '1', null, null, null, null, '1', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '1', '1', '0', '0', '1', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '2', '1', '0', '0', '1', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '3', '0', '0', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '4', '0', '0', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '5', '0', '0', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '6', '1', '0', '0', '1', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '7', null, null, null, null, '1', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '8', null, null, null, null, '1', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '9', null, null, null, null, '1', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '10', '1', '0', '1', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '11', '1', '0', '1', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '12', '1', '1', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '13', '1', '1', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '14', '1', '1', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '15', '1', '0', '1', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '16', '1', '0', '1', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '17', '1', '0', '1', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '18', '1', '0', '1', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '19', '1', '0', '1', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '20', '1', '0', '1', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '21', '1', '1', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-04', '22', '1', '1', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-05', '1', null, null, null, null, '1', null);
INSERT INTO `api_runreports` VALUES ('2016-05-05', '2', '1', '0', '1', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-05', '3', '1', '0', '1', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-05', '4', '1', '1', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-05', '5', '1', '1', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-05', '6', '1', '1', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-05', '7', '1', '1', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-05', '8', '1', '1', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-05', '9', '1', '1', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-05', '10', '1', '1', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-05', '11', '1', '1', '0', '0', '2', null);
INSERT INTO `api_runreports` VALUES ('2016-05-05', '12', '1', '0', '1', '0', '2', null);

-- ----------------------------
-- Table structure for `device`
-- ----------------------------
DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `ID` int(1) NOT NULL,
  `udid` char(50) NOT NULL,
  `deviceName` char(50) NOT NULL,
  `platformVersion` char(50) NOT NULL,
  `platformName` char(50) NOT NULL,
  `port` char(50) NOT NULL,
  `bport` char(50) NOT NULL,
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0空闲，1已与SERVER关联，2已分配线程执行',
  `pid` int(10) DEFAULT NULL,
  `updatetime` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `device` (`ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of device
-- ----------------------------
INSERT INTO `device` VALUES ('1', 'MYV0215A13000951', 'Android', '4.4.2', 'Android', '1111', '1112', '1', null, '2016-04-25 15:38:25');
INSERT INTO `device` VALUES ('2', '10.36.41.199:5555', 'Android', '4.4.2', 'Android', '2222', '2223', '1', null, null);

-- ----------------------------
-- Table structure for `reports`
-- ----------------------------
DROP TABLE IF EXISTS `reports`;
CREATE TABLE `reports` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `udid` varchar(50) DEFAULT NULL,
  `classname` varchar(50) NOT NULL,
  `method` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL,
  `message` varchar(5000) DEFAULT NULL,
  `round` int(2) NOT NULL,
  `exectime` int(10) DEFAULT NULL,
  `pic` varchar(100) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reports
-- ----------------------------
INSERT INTO `reports` VALUES ('89', 'MYV0215A13000951', 'yrdV10', 'getAmount', 'skip', '', '1', '0', '', '2016-04-22 11:04:01');
INSERT INTO `reports` VALUES ('90', 'MYV0215A13000951', 'yrdV10', 'setCoupon', 'skip', '', '1', '0', '', '2016-04-22 11:04:01');
INSERT INTO `reports` VALUES ('91', 'MYV0215A13000951', 'yrdV10', 'setCoupon', 'skip', '', '1', '0', '', '2016-04-22 11:05:22');
INSERT INTO `reports` VALUES ('92', 'MYV0215A13000951', 'yrdV10', 'setCoupon', 'skip', '', '1', '0', '', '2016-04-22 11:06:21');
INSERT INTO `reports` VALUES ('93', 'Default test', 'yrdV10', 'getAmount', 'fail', null, '1', '0', '', '2016-04-22 14:04:38');
INSERT INTO `reports` VALUES ('94', 'Default test', 'yrdV10', 'setCoupon', 'success', '', '1', '35', '', '2016-04-22 14:05:19');
INSERT INTO `reports` VALUES ('95', 'Default test', 'yrdV10', 'getAmount', 'skip', '', '1', '0', '', '2016-04-22 14:14:03');
INSERT INTO `reports` VALUES ('96', 'Default test', 'yrdV10', 'setCoupon', 'skip', '', '1', '3', '', '2016-04-22 14:14:06');
INSERT INTO `reports` VALUES ('97', 'Default test', 'yrdV10', 'getAmount', 'skip', '', '1', '0', '', '2016-04-22 14:20:33');
INSERT INTO `reports` VALUES ('98', 'Default test', 'yrdV10', 'setCoupon', 'skip', '', '1', '0', '', '2016-04-22 14:20:33');
INSERT INTO `reports` VALUES ('99', 'Default test', 'yrdV10', 'getAmount', 'success', '', '1', '23', '', '2016-04-22 14:21:31');
INSERT INTO `reports` VALUES ('100', 'Default test', 'yrdV10', 'setCoupon', 'success', '', '1', '41', '', '2016-04-22 14:22:18');
INSERT INTO `reports` VALUES ('101', 'MYV0215A13000951', 'yrdV10', 'setCoupon', 'success', '', '1', '42', '', '2016-04-22 16:11:49');
INSERT INTO `reports` VALUES ('102', 'MYV0215A13000951', 'yrdV10', 'setCoupon', 'success', '', '1', '42', '', '2016-04-22 16:11:49');
INSERT INTO `reports` VALUES ('103', 'MYV0215A13000951', 'yrdV10', 'setCoupon', 'success', '', '1', '37', 'd:\\appium\\yrdV10\\MYV0215A13000951\\20160422\\setCoupon-173559.jpg', '2016-04-22 16:35:59');
INSERT INTO `reports` VALUES ('104', 'MYV0215A13000951', 'yrdV10', 'setCoupon', 'skip', '', '1', '0', '', '2016-04-25 13:51:11');
INSERT INTO `reports` VALUES ('105', 'MYV0215A13000951', 'yrdV10', 'setCoupon', 'success', '', '1', '37', 'd:\\appium\\yrdV10\\MYV0215A13000951\\20160425\\setCoupon-145327.jpg', '2016-04-25 13:53:27');
INSERT INTO `reports` VALUES ('106', 'MYV0215A13000951', 'yrdV10', 'setCoupon', 'success', '', '1', '40', 'd:\\appium\\yrdV10\\MYV0215A13000951\\20160425\\setCoupon-150215.jpg', '2016-04-25 14:02:15');
INSERT INTO `reports` VALUES ('107', 'MYV0215A13000951', 'yrdV10', 'setCoupon', 'success', '', '1', '38', 'd:\\appium\\yrdV10\\MYV0215A13000951\\20160425\\setCoupon-152643.jpg', '2016-04-25 14:26:43');
INSERT INTO `reports` VALUES ('108', 'MYV0215A13000951', 'yrdV10', 'setCoupon', 'fail', 'An element could not be located on the page using the given search parameters. (WARNING: The server did not provide any stacktrace information)\nCommand duration or timeout: 21.12 seconds\nFor documentation on this error, please visit: http://seleniumhq.org/exceptions/no_such_element.html\nBuild info: version: \'2.44.0\', revision: \'76d78cf323ce037c5f92db6c1bba601c2ac43ad8\', time: \'2014-10-23 13:11:40\'\nSystem info: host: \'20160229-115937\', ip: \'10.36.40.84\', os.name: \'Windows 7\', os.arch: \'amd64\', os.version: \'6.1\', java.version: \'1.7.0_17\'\nSession ID: df31a769-7ac7-468e-ba17-68c252d0bc74\nDriver info: io.appium.java_client.android.AndroidDriver\nCapabilities [{platform=LINUX, javascriptEnabled=true, appActivity=.ui.MainActivity, browserName=Android, networkConnectionEnabled=true, udid=MYV0215A13000951, desired={platformVersion=4.4.2, platformName=Android, deviceName=Android, appActivity=.ui.MainActivity, unicodeKeyboard=true, udid=MYV0215A13000951, resetKeyboard=true, appPackage=com.creditwealth.client}, locationContextEnabled=false, appPackage=com.creditwealth.client, platformVersion=5.0.2, databaseEnabled=false, deviceName=MYV0215A13000951, platformName=Android, webStorageEnabled=false, unicodeKeyboard=true, resetKeyboard=true, warnings={}, takesScreenshot=true}]', '1', '47', 'd:\\appium\\yrdV10\\MYV0215A13000951\\20160425\\setCoupon-154851.jpg', '2016-04-25 14:48:51');
INSERT INTO `reports` VALUES ('109', 'MYV0215A13000951', 'yrdV10', 'setCoupon', 'success', '', '1', '40', 'd:\\appium\\yrdV10\\MYV0215A13000951\\20160425\\setCoupon-163823.jpg', '2016-04-25 15:38:23');

-- ----------------------------
-- Table structure for `runreports`
-- ----------------------------
DROP TABLE IF EXISTS `runreports`;
CREATE TABLE `runreports` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `udid` varchar(50) DEFAULT NULL,
  `classname` varchar(50) NOT NULL,
  `method` varchar(50) NOT NULL,
  `status` varchar(20) NOT NULL,
  `message` varchar(5000) DEFAULT NULL,
  `round` int(2) NOT NULL,
  `exectime` int(10) DEFAULT NULL,
  `pic` varchar(100) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of runreports
-- ----------------------------

-- ----------------------------
-- Table structure for `server`
-- ----------------------------
DROP TABLE IF EXISTS `server`;
CREATE TABLE `server` (
  `pid` char(10) NOT NULL DEFAULT '',
  `casenumber` int(2) DEFAULT NULL,
  `udid` char(50) DEFAULT '',
  `status` int(2) NOT NULL COMMENT '0空闲，1已和设备关联，2正在运行，3运行结束，4待删除',
  PRIMARY KEY (`pid`),
  UNIQUE KEY `pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of server
-- ----------------------------
INSERT INTO `server` VALUES ('4552', '0', '10.36.41.199:5555', '0');
INSERT INTO `server` VALUES ('5128', '0', 'MYV0215A13000951', '0');
