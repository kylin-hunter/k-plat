use kp;

# add tenant role

INSERT INTO kp.kplat_tenant_role (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                  sys_created_user_name, sys_created_time, sys_update_user_id,
                                  sys_update_user_name, sys_update_time, sys_delete_flag,
                                  sys_op_lock, code, name, type, status, description)
VALUES ('1', '1', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'test', '测试角色', 0, 0, '系统初始化');


#add role-permission

INSERT INTO kplat_tenant_role_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                          sys_created_user_name, sys_created_time,
                                          sys_update_user_id,
                                          sys_update_user_name, sys_update_time, sys_delete_flag,
                                          sys_op_lock,
                                          role_id, permission_id)
VALUES ('1001', '1', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, '1', 'tenant_users::create'),

       ('1002', '1', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, '1', 'tenant_users::delete'),

       ('1003', '1', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, '1', 'tenant_users::update'),

       ('1004', '1', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, '1', 'tenant_users::get');


#add tenant_user role

INSERT INTO kplat_tenant_user_role (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                    sys_created_user_name, sys_created_time, sys_update_user_id,
                                    sys_update_user_name, sys_update_time, sys_delete_flag,
                                    sys_op_lock,
                                    user_id, role_id)
VALUES ('1', '1', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant-user1', '1');

INSERT INTO kplat_tenant_user_role (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                    sys_created_user_name, sys_created_time, sys_update_user_id,
                                    sys_update_user_name, sys_update_time, sys_delete_flag,
                                    sys_op_lock,
                                    user_id, role_id)
VALUES ('2', '1', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant-user2', '1');
