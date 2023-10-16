use kp;


# add tenant

INSERT INTO kp.kplat_tenant (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                             sys_created_user_name, sys_created_time, sys_update_user_id,
                             sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                             code, name, type, status, description)
VALUES ('1', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'default', '默认租户', 1, 0, '系统初始化');
