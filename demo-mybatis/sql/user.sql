CREATE TABLE `user`
(
    `id`           int(11) NOT NULL,
    `name`         varchar(255) DEFAULT NULL,
    `password`     varchar(255) DEFAULT NULL,
    `salt`         varchar(255) DEFAULT NULL,
    `email`        varchar(255) DEFAULT NULL,
    `phone_number` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;