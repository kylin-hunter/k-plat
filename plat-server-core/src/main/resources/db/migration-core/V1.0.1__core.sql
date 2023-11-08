CREATE DATABASE IF NOT EXISTS kp DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_bin;
use kp;


CREATE TABLE IF NOT EXISTS `kplat_user`
(
    `id`                    varchar(64)  NOT NULL COMMENT '主键',
    `sys_tenant_id`         varchar(64)  NOT NULL DEFAULT '' COMMENT '租户ID',
    `sys_auto_updated`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
    `sys_created_user_id`   varchar(64)  NOT NULL default 0 COMMENT '创建人userid',
    `sys_created_user_name` varchar(64)  NOT NULL default '' COMMENT '创建人username',
    `sys_created_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `sys_update_user_id`    varchar(64)  NOT NULL default 0 COMMENT '最后编辑人userid',
    `sys_update_user_name`  varchar(64)  NOT NULL default '' COMMENT '最后编辑人username',
    `sys_update_time`       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后编辑时间',
    `sys_delete_flag`       tinyint      NOT NULL DEFAULT 0 COMMENT '0 未删除 1删除',
    `sys_op_lock`           int          NULL COMMENT '乐观锁',
    `user_name`             varchar(64)  NOT NULL DEFAULT '' COMMENT '登录用户名',
    `nick_name`             varchar(64)  NOT NULL DEFAULT '' COMMENT '昵称，用户自己可以改',
    `real_name`             varchar(64)  NOT NULL DEFAULT '' COMMENT '真实姓名，用户自己不可以改',
    `password`              varchar(64)  NOT NULL DEFAULT '' COMMENT '密码',
    `source`                varchar(100) NOT NULL DEFAULT 0 COMMENT '用户来源',
    `type`                  tinyint      NOT NULL DEFAULT 0 COMMENT '用户类型 0 普通 1超级管理员 ',
    `status`                tinyint      NOT NULL DEFAULT 0 COMMENT '用户状态',
    `description`           varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_user_code` (`user_name`) COMMENT '唯一的user name'
);



CREATE TABLE IF NOT EXISTS `kplat_permission`
(
    `id`                    varchar(64)  NOT NULL COMMENT '主键',
    `sys_tenant_id`         varchar(64)  NOT NULL DEFAULT '' COMMENT '租户ID',
    `sys_auto_updated`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
    `sys_created_user_id`   varchar(64)  NOT NULL default 0 COMMENT '创建人userid',
    `sys_created_user_name` varchar(64)  NOT NULL default '' COMMENT '创建人username',
    `sys_created_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `sys_update_user_id`    varchar(64)  NOT NULL default 0 COMMENT '最后编辑人userid',
    `sys_update_user_name`  varchar(64)  NOT NULL default '' COMMENT '最后编辑人username',
    `sys_update_time`       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后编辑时间',
    `sys_delete_flag`       tinyint      NOT NULL DEFAULT 0 COMMENT '0 未删除 1删除',
    `sys_op_lock`           int          NULL COMMENT '乐观锁',
    `code`                  varchar(255) NOT NULL COMMENT '权限代码',
    `name`                  varchar(255) NOT NULL DEFAULT '' COMMENT '权限名字',
    `type`                  tinyint      NOT NULL DEFAULT 0 COMMENT '类型 0自建  1 系统自带 ',
    `description`           varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
    PRIMARY KEY (`id`),
    KEY `idx_code` (`code`) COMMENT 'code'

);


CREATE TABLE IF NOT EXISTS `kplat_role`
(
    `id`                    varchar(64)  NOT NULL COMMENT '主键',
    `sys_tenant_id`         varchar(64)  NOT NULL DEFAULT '' COMMENT '租户ID',
    `sys_auto_updated`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
    `sys_created_user_id`   varchar(64)  NOT NULL default 0 COMMENT '创建人userid',
    `sys_created_user_name` varchar(64)  NOT NULL default '' COMMENT '创建人username',
    `sys_created_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `sys_update_user_id`    varchar(64)  NOT NULL default 0 COMMENT '最后编辑人userid',
    `sys_update_user_name`  varchar(64)  NOT NULL default '' COMMENT '最后编辑人username',
    `sys_update_time`       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后编辑时间',
    `sys_delete_flag`       tinyint      NOT NULL DEFAULT 0 COMMENT '0 未删除 1删除',
    `sys_op_lock`           int          NULL COMMENT '乐观锁',
    `code`                  varchar(64)  NOT NULL DEFAULT '' COMMENT 'code',
    `name`                  varchar(64)  NOT NULL DEFAULT '' COMMENT 'name',
    `type`                  tinyint      NOT NULL DEFAULT 0 COMMENT '类型',
    `status`                tinyint      NOT NULL DEFAULT 0 COMMENT '角色状态，预留',
    `description`           varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_role_code` (`code`) COMMENT '唯一的code'
);


CREATE TABLE IF NOT EXISTS `kplat_role_permission`
(
    `id`                    varchar(64) NOT NULL COMMENT '主键',
    `sys_tenant_id`         varchar(64) NOT NULL DEFAULT '' COMMENT '租户ID',
    `sys_auto_updated`      datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
    `sys_created_user_id`   varchar(64) NOT NULL default 0 COMMENT '创建人userid',
    `sys_created_user_name` varchar(64) NOT NULL default '' COMMENT '创建人username',
    `sys_created_time`      datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `sys_update_user_id`    varchar(64) NOT NULL default 0 COMMENT '最后编辑人userid',
    `sys_update_user_name`  varchar(64) NOT NULL default '' COMMENT '最后编辑人username',
    `sys_update_time`       datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后编辑时间',
    `sys_delete_flag`       tinyint     NOT NULL DEFAULT 0 COMMENT '0 未删除 1删除',
    `sys_op_lock`           int          NOT NULL DEFAULT 0 COMMENT '乐观锁',
    `role_id`               varchar(64) NOT NULL COMMENT '角色id',
    `permission_id`         varchar(64) NOT NULL DEFAULT '' COMMENT 'permission id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_role_permission` (`role_id`, `permission_id`) COMMENT '角色权限唯一'

);

CREATE TABLE IF NOT EXISTS `kplat_user_role`
(
    `id`                    varchar(64) NOT NULL COMMENT '主键',
    `sys_tenant_id`         varchar(64) NOT NULL DEFAULT '' COMMENT '租户ID',
    `sys_auto_updated`      datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
    `sys_created_user_id`   varchar(64) NOT NULL default 0 COMMENT '创建人userid',
    `sys_created_user_name` varchar(64) NOT NULL default '' COMMENT '创建人username',
    `sys_created_time`      datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `sys_update_user_id`    varchar(64) NOT NULL default 0 COMMENT '最后编辑人userid',
    `sys_update_user_name`  varchar(64) NOT NULL default '' COMMENT '最后编辑人username',
    `sys_update_time`       datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后编辑时间',
    `sys_delete_flag`       tinyint     NOT NULL DEFAULT 0 COMMENT '0 未删除 1删除',
    `sys_op_lock`           int          NOT NULL DEFAULT 0 COMMENT '乐观锁',
    `user_id`               varchar(64) NOT NULL COMMENT '用户id',
    `role_id`               varchar(64) NOT NULL DEFAULT '' COMMENT '角色 id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_user_role` (`user_id`, `role_id`) COMMENT '用户角色唯一',
    constraint kplat_user_role_kplat_role_id_fk foreign key (role_id) references kp.kplat_role (id),
    constraint kplat_user_role_kplat_user_id_fk foreign key (user_id) references kp.kplat_user (id)

);



CREATE TABLE IF NOT EXISTS `kplat_sys_config`
(
    `id`                    varchar(64)   NOT NULL COMMENT '主键',
    `sys_tenant_id`         varchar(64)   NOT NULL DEFAULT '' COMMENT '租户ID',
    `sys_auto_updated`      datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
    `sys_created_user_id`   varchar(64)   NOT NULL default 0 COMMENT '创建人userid',
    `sys_created_user_name` varchar(64)   NOT NULL default '' COMMENT '创建人username',
    `sys_created_time`      datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `sys_update_user_id`    varchar(64)   NOT NULL default 0 COMMENT '最后编辑人userid',
    `sys_update_user_name`  varchar(64)   NOT NULL default '' COMMENT '最后编辑人username',
    `sys_update_time`       datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后编辑时间',
    `sys_delete_flag`       tinyint       NOT NULL DEFAULT 0 COMMENT '0 未删除 1删除',
    `sys_op_lock`           int           NULL COMMENT '乐观锁',
    `type`                  tinyint       NOT NULL DEFAULT 0 COMMENT '类型 0 字符串',
    `status`                tinyint       NOT NULL DEFAULT 0 COMMENT '状态',
    `description`           varchar(255)  NOT NULL DEFAULT '' COMMENT '描述',
    `code`                  varchar(64)   NOT NULL DEFAULT '' COMMENT '配置编码',
    `value`                 varchar(4096) NOT NULL DEFAULT '' COMMENT '配置项的值',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_code` (`code`) COMMENT '唯一的code'
);

CREATE TABLE IF NOT EXISTS `kplat_sys_user_config`
(
    `id`                    varchar(64)   NOT NULL COMMENT '主键',
    `sys_tenant_id`         varchar(64)   NOT NULL DEFAULT '' COMMENT '租户ID',
    `sys_auto_updated`      datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
    `sys_created_user_id`   varchar(64)   NOT NULL default 0 COMMENT '创建人userid',
    `sys_created_user_name` varchar(64)   NOT NULL default '' COMMENT '创建人username',
    `sys_created_time`      datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `sys_update_user_id`    varchar(64)   NOT NULL default 0 COMMENT '最后编辑人userid',
    `sys_update_user_name`  varchar(64)   NOT NULL default '' COMMENT '最后编辑人username',
    `sys_update_time`       datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后编辑时间',
    `sys_delete_flag`       tinyint       NOT NULL DEFAULT 0 COMMENT '0 未删除 1删除',
    `sys_op_lock`           int           NULL COMMENT '乐观锁',
    `type`                  tinyint       NOT NULL DEFAULT 0 COMMENT '类型 0 字符串',
    `status`                tinyint       NOT NULL DEFAULT 0 COMMENT '状态',
    `description`           varchar(255)  NOT NULL DEFAULT '' COMMENT '描述',
    `user_id`               varchar(64)   NOT NULL default 0 COMMENT 'userid',
    `code`                  varchar(64)   NOT NULL DEFAULT '' COMMENT '配置编码',
    `value`                 varchar(4096) NOT NULL DEFAULT '' COMMENT '配置项的值',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_code` (`user_id`, `code`) COMMENT '唯一的code'
);