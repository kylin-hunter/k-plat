
CREATE TABLE IF NOT EXISTS `kp_user` (
    `id` varchar(64) NOT NULL COMMENT '主键',
    `sys_tenant_id` varchar(64) NOT NULL DEFAULT '' COMMENT '租户ID',
    `sys_auto_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
    `sys_created_user_id` varchar(64) NOT NULL default 0 COMMENT '创建人userid',
    `sys_created_user_name` varchar(64) NOT NULL default '' COMMENT '创建人username',
    `sys_created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `sys_update_user_id` varchar(64) NOT NULL default 0 COMMENT '最后编辑人userid',
    `sys_update_user_name` varchar(64) NOT NULL default '' COMMENT '最后编辑人username',
    `sys_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后编辑时间',
    `sys_delete_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0 未删除 1删除',
    `sys_op_lock` int NULL COMMENT '乐观锁',
    `user_id` varchar(64) NOT NULL COMMENT '用户id',
    `user_name` varchar(64) NOT NULL COMMENT '用户名',
    `source` varchar(100) NOT NULL DEFAULT 0 COMMENT '用户来源',
    `type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '用户类似',
    `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '用户状态',
    `description` varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
    PRIMARY KEY (`id`)
);


CREATE TABLE IF NOT EXISTS `kp_role` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `sys_tenant_id` varchar(64) NOT NULL DEFAULT '' COMMENT '租户ID',
  `sys_auto_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '系统更新时间',
  `sys_created_user_id` varchar(64) NOT NULL default 0 COMMENT '创建人userid',
  `sys_created_user_name` varchar(64) NOT NULL default '' COMMENT '创建人username',
  `sys_created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `sys_update_user_id` varchar(64) NOT NULL default 0 COMMENT '最后编辑人userid',
  `sys_update_user_name` varchar(64) NOT NULL default '' COMMENT '最后编辑人username',
  `sys_update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后编辑时间',
  `sys_delete_flag` tinyint(1) NOT NULL DEFAULT 0 COMMENT '0 未删除 1删除',
  `sys_op_lock` int NULL COMMENT '乐观锁',
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `role_name` varchar(64) NOT NULL COMMENT '角色名',
  `type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '角色类型',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '角色状态，预留',
  `description` varchar(255) NOT NULL DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`id`)
);