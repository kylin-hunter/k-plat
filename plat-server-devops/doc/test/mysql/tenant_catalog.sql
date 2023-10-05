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
    `level`                 int           NOT NULL DEFAULT 0 COMMENT 'path',
    `parent_id`             varchar(64)   NOT NULL DEFAULT '0' COMMENT 'parent_id',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_tenant_catalog_code` (`sys_tenant_id`, `type`, `level`, `code`) COMMENT '唯一的code'
);
