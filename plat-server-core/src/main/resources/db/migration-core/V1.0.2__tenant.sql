use kp;


CREATE TABLE IF NOT EXISTS `kplat_tenant`
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
    `type`                  tinyint      NOT NULL DEFAULT 0 COMMENT '类型 0 普通租户 1系统租户',
    `status`                tinyint      NOT NULL DEFAULT 0 COMMENT '状态，预留',
    `description`           varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_tenant_code` (`code`) COMMENT '唯一的code'
);

CREATE TABLE IF NOT EXISTS `kplat_tenant_user`
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
    `user_id`               varchar(64)  NOT NULL DEFAULT '' COMMENT 'userId',
    `type`                  tinyint      NOT NULL DEFAULT 2 COMMENT '类型 2普通租户用户 3租户管理员',
    `status`                tinyint      NOT NULL DEFAULT 0 COMMENT '状态，预留',
    `description`           varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_tenant_user` (`sys_tenant_id`, `user_id`) COMMENT '唯一的code',
    KEY `idx_userId` (`user_id`) USING BTREE COMMENT 'userId'
);



CREATE TABLE IF NOT EXISTS `kplat_tenant_role`
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
    UNIQUE KEY `uniq_tenant_role_code` (`sys_tenant_id`, `code`) COMMENT '唯一的code'
);


CREATE TABLE IF NOT EXISTS `kplat_tenant_user_role`
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
    `sys_op_lock`           int         NULL COMMENT '乐观锁',
    `user_id`               varchar(64) NOT NULL COMMENT '用户id',
    `role_id`               varchar(64) NOT NULL DEFAULT '' COMMENT '角色 id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_user_role` (`sys_tenant_id`, `user_id`, `role_id`) COMMENT '用户角色唯一'
);


CREATE TABLE IF NOT EXISTS `kplat_tenant_role_permission`
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
    `sys_op_lock`           int         NULL COMMENT '乐观锁',
    `role_id`               varchar(64) NOT NULL COMMENT '角色id',
    `permission_id`         varchar(64) NOT NULL DEFAULT '' COMMENT 'permission id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_role_permission` (`sys_tenant_id`, `role_id`, `permission_id`) COMMENT '角色权限唯一'

);

CREATE TABLE IF NOT EXISTS `kplat_tenant_catalog`
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
    `code`                  varchar(64)   NOT NULL DEFAULT '' COMMENT 'code',
    `name`                  varchar(64)   NOT NULL DEFAULT '' COMMENT 'name',
    `type`                  tinyint       NOT NULL DEFAULT 0 COMMENT '类型',
    `status`                tinyint       NOT NULL DEFAULT 0 COMMENT '状态，预留',
    `description`           varchar(255)  NOT NULL DEFAULT '' COMMENT '描述',
    `path`                  varchar(1024) NOT NULL DEFAULT '' COMMENT 'path',
    `level`                 int           NOT NULL DEFAULT 0 COMMENT 'level',
    `parent_id`             varchar(64)   NOT NULL DEFAULT '0' COMMENT 'parent_id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_tenant_catalog_code` (`sys_tenant_id`, `type`, `code`) COMMENT '唯一的code'
);
CREATE TABLE IF NOT EXISTS `kplat_tenant_config`
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
    UNIQUE KEY `uniq_code` (`sys_tenant_id`, `code`) COMMENT '唯一的code'
);

CREATE TABLE IF NOT EXISTS `kplat_tenant_user_config`
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
    UNIQUE KEY `uniq_code` (`sys_tenant_id`, `user_id`, `code`) COMMENT '唯一的code'
);