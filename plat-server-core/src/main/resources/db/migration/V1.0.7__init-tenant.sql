use kp;


# add tenant

INSERT INTO kp.kplat_tenant (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                             sys_created_user_name, sys_created_time, sys_update_user_id,
                             sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                             code, name, type, status, description)
VALUES ('1', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'default', 'DEFAULT', 1, 0, '默认租户');

# add tenant user

INSERT INTO kp.kplat_tenant_user (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                  sys_created_user_name, sys_created_time, sys_update_user_id,
                                  sys_update_user_name, sys_update_time, sys_delete_flag,
                                  sys_op_lock,  user_id, type, status, description)
VALUES ('1', '1', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null,  '1', 3, 0, '');



INSERT INTO kp.kplat_tenant_user (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                  sys_created_user_name, sys_created_time, sys_update_user_id,
                                  sys_update_user_name, sys_update_time, sys_delete_flag,
                                  sys_op_lock,  user_id, type, status, description)
VALUES ('2', '1', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null,  '2', 2, 0, '');


# add tenant role

INSERT INTO kp.kplat_tenant_role (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                  sys_created_user_name, sys_created_time, sys_update_user_id,
                                  sys_update_user_name, sys_update_time, sys_delete_flag,
                                  sys_op_lock, code, name, type, status, description)
VALUES ('1', '1', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'admin', 'admin', 0, 0, 'admin');

INSERT INTO kp.kplat_tenant_role (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                  sys_created_user_name, sys_created_time, sys_update_user_id,
                                  sys_update_user_name, sys_update_time, sys_delete_flag,
                                  sys_op_lock, code, name, type, status, description)
VALUES ('2', '1', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'test', 'test', 0, 0, 'test');



