use kp;



# add tenant user

INSERT INTO kp.kplat_tenant_user (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                  sys_created_user_name, sys_created_time, sys_update_user_id,
                                  sys_update_user_name, sys_update_time, sys_delete_flag,
                                  sys_op_lock,  user_id, type, status, description)
VALUES ('tenant-user-id-1', '1', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null,  '1', 3, 0, '系统初始化');



INSERT INTO kp.kplat_tenant_user (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                  sys_created_user_name, sys_created_time, sys_update_user_id,
                                  sys_update_user_name, sys_update_time, sys_delete_flag,
                                  sys_op_lock,  user_id, type, status, description)
VALUES ('tenant-user-id-2', '1', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null,  '2', 2, 0, '系统初始化');