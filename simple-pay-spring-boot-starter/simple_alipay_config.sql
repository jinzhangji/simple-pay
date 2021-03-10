
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for simple_alipay_config
-- ----------------------------
DROP TABLE IF EXISTS `simple_alipay_config`;
CREATE TABLE `simple_alipay_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_id` varchar(64) DEFAULT NULL COMMENT '应用id',
  `private_key` varchar(1024) DEFAULT NULL COMMENT '商户私钥',
  `ali_pay_public_key` varchar(1024) DEFAULT NULL COMMENT '阿里提供的公钥',
  `app_type` varchar(8) DEFAULT NULL COMMENT '应用类型 mp(网页\\移动应用)、oa(服务号)、applets(小程序)',
  `notify_url` varchar(1024) DEFAULT NULL COMMENT '支付回调地址/部分支付退款回调也有可能走此接口',
  `refund_notify_url` varchar(1024) DEFAULT NULL COMMENT '退款回调地址',
  `redirect_url` varchar(1024) DEFAULT NULL COMMENT 'h5重定向地址',
  `state` tinyint(1) DEFAULT '0' COMMENT '状态 0:禁用 1:启用',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除 0:否 1:是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
