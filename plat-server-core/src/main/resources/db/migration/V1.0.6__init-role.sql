use kp;

#add role

INSERT INTO kp.kplat_role (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                           sys_created_user_name, sys_created_time, sys_update_user_id,
                           sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                           code, name, type, status, description)
VALUES ('1', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'admin', 'admin', 0, 0, 'admin');

#add role-permission

INSERT INTO kplat_role_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                      sys_created_user_name, sys_created_time, sys_update_user_id,
                                      sys_update_user_name, sys_update_time, sys_delete_flag,
                                      sys_op_lock,
                                      role_id, permission_id)
VALUES ('1', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, '1', '201');

INSERT INTO kplat_role_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                      sys_created_user_name, sys_created_time, sys_update_user_id,
                                      sys_update_user_name, sys_update_time, sys_delete_flag,
                                      sys_op_lock,
                                      role_id, permission_id)
VALUES ('2', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, '1', '205');

