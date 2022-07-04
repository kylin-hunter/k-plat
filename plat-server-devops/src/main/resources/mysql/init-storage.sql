use kp;

CREATE TABLE IF NOT EXISTS `kplat_file_metadata`
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
    `type`                  tinyint      NOT NULL DEFAULT 0 COMMENT '类型 0 ',
    `content_type`          varchar(255) NOT NULL DEFAULT '' COMMENT 'content-type',
    `name`                  varchar(255) NOT NULL DEFAULT '' COMMENT '文件名',
    `extension`             varchar(255) NOT NULL DEFAULT '' COMMENT '扩展名',
    `size`                  bigint(20)   NOT NULL DEFAULT 0 COMMENT 'size',
    `description`           varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
    `md5`                   varchar(64)  NOT NULL DEFAULT '' COMMENT 'md5',
    `bucket`                varchar(64)  NOT NULL DEFAULT '' COMMENT 'bucket',
    `path`                  varchar(256) NOT NULL DEFAULT '' COMMENT '地址',
    PRIMARY KEY (`id`),
    KEY `idx_md5` (`md5`) COMMENT 'md5'

);


CREATE TABLE IF NOT EXISTS `kplat_file_relation`
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
    `type`                  tinyint     NOT NULL DEFAULT 0 COMMENT '类型 0 ',
    `master_id`             varchar(64) NOT NULL DEFAULT '' COMMENT '知识ID',
    `file_id`               varchar(64) NOT NULL DEFAULT '' COMMENT '文件ID',
    PRIMARY KEY (`id`),
    KEY `idx_file_id` (`file_id`),
    UNIQUE KEY `uniq_relation` (`type`, `master_id`, `file_id`) COMMENT '唯一的标志'

);