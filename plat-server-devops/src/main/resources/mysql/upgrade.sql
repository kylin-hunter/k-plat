ALTER TABLE `kplat_user` CHANGE COLUMN `user_id` `user_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin
    NOT NULL COMMENT '用户code';
ALTER TABLE `kplat_user` ADD COLUMN `password` varchar(64) NOT NULL  COMMENT '密码';
ALTER TABLE `kplat_user` ADD UNIQUE `uniq_user_code` (`user_code`) comment '唯一的user code';