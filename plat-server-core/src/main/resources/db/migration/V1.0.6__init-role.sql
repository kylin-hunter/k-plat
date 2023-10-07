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
VALUES ('1001', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, '1', 'users::create');

INSERT INTO kplat_role_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                   sys_created_user_name, sys_created_time, sys_update_user_id,
                                   sys_update_user_name, sys_update_time, sys_delete_flag,
                                   sys_op_lock,
                                   role_id, permission_id)
VALUES ('1002', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, '1', 'users::delete');



INSERT INTO kplat_role_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                   sys_created_user_name, sys_created_time, sys_update_user_id,
                                   sys_update_user_name, sys_update_time, sys_delete_flag,
                                   sys_op_lock,
                                   role_id, permission_id)
VALUES ('1003', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, '1', 'users::update');



INSERT INTO kplat_role_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                   sys_created_user_name, sys_created_time, sys_update_user_id,
                                   sys_update_user_name, sys_update_time, sys_delete_flag,
                                   sys_op_lock,
                                   role_id, permission_id)
VALUES ('1004', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, '1', 'users::get');



INSERT INTO kplat_role_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                   sys_created_user_name, sys_created_time, sys_update_user_id,
                                   sys_update_user_name, sys_update_time, sys_delete_flag,
                                   sys_op_lock,
                                   role_id, permission_id)
VALUES ('1005', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, '1', 'users::list');


#add user-role

INSERT INTO kplat_user_role (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                             sys_created_user_name, sys_created_time, sys_update_user_id,
                             sys_update_user_name, sys_update_time, sys_delete_flag,
                             sys_op_lock,
                             user_id, role_id)
VALUES ('1', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, '2', '1');
