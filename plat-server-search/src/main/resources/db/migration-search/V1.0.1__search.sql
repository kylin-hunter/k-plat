use kp;

CREATE TABLE IF NOT EXISTS `kplat_search_syn_progress`
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
    `sys_op_lock`           int         NOT NULL DEFAULT 0 COMMENT '乐观锁',
    `next_sync_time`        datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上次同步时间',
    `last_sync_id`          varchar(64) NOT NULL DEFAULT '' COMMENT '上次同步的ID',
    PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `kplat_search_syn_task`
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
    `sys_op_lock`           int         NOT NULL DEFAULT 0 COMMENT '乐观锁',
    `type`                  int(11)     NOT NULL DEFAULT 0 COMMENT '3、文档search同步、4、Faq search同步',
    `status`                tinyint(4)  NOT NULL DEFAULT 0 COMMENT '0待同步，1正同步，2成功，3阶段失败，4最终失败',
    `msg`                   varchar(255)         DEFAULT NULL COMMENT '同步信息',
    `try_count`             int(11)     NOT NULL DEFAULT 0 COMMENT '同步次数',
    master_id               varchar(64) null comment '知识id',
    operator                tinyint(4)  null comment '操作 1 添加、2 删除',

    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_relation` (`type`, `master_id`) COMMENT '唯一的标志'

);