CREATE TABLE `user`
(
    `id`           int(11) NOT NULL AUTO_INCREMENT,
    `name`         varchar(255) DEFAULT NULL COMMENT '用户名',
    `phone_number` varchar(11)  DEFAULT '' COMMENT '手机号',
    `status`       bit(1)       DEFAULT NULL COMMENT '状态，1 有效；0 无效',
    `create_time`  datetime     DEFAULT NULL,
    `update_time`  datetime     DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;