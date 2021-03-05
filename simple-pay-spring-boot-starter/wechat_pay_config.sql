/*
Navicat MySQL Data Transfer
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wechat_pay_config
-- ----------------------------
DROP TABLE IF EXISTS `wechat_pay_config`;
CREATE TABLE `wechat_pay_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) DEFAULT NULL,
  `app_id` varchar(32) DEFAULT NULL COMMENT '公众平台appid',
  `secret` varchar(64) DEFAULT NULL COMMENT '公众平台凭证',
  `mchid` varchar(32) DEFAULT NULL COMMENT '商户号',
  `sign_key` varchar(64) DEFAULT NULL COMMENT '签名key',
  `app_type` varchar(32) DEFAULT NULL,
  `pk12_path` varchar(64) DEFAULT NULL COMMENT '退款证书路径',
  `refund_notify_url` varchar(256) DEFAULT NULL COMMENT '退款通知地址',
  `redirect_url` varchar(256) DEFAULT NULL COMMENT 'h5重定向地址',
  `notify_url` varchar(256) DEFAULT NULL COMMENT '回调url',
  `state` tinyint(1) DEFAULT '1' COMMENT '状态 0:禁用 1:使用',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除 0:否 1:是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
