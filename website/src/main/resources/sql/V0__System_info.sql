USE website;

drop table IF EXISTS `system_info`;
create TABLE `system_info`
(
    `id` INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
    COMMENT = '系统信息表'
    AUTO_INCREMENT = 1
    DEFAULT CHARSET = utf8mb4;