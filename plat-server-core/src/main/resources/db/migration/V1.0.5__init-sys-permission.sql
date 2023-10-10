use kp;



# add permission permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('permissions::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'permissions::create', 'permissions::create',  1, '系统初始化');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('permissions::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'permissions::update', 'permissions::update',  1, '系统初始化');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('permissions::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'permissions::delete', 'permissions::delete',  1, '系统初始化');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('permissions::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'permissions::get', 'permissions::get',  1, '系统初始化');




# add role permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('roles::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::create', 'roles::create',  1, '系统初始化');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('roles::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::update', 'roles::update',  1, '系统初始化');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('roles::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::delete', 'roles::delete',  1, '系统初始化');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('roles::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::get', 'roles::get',  1, '系统初始化');



# add role_permissions permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('role_permissions::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'role_permissions::create', 'role_permissions::create',  1, '系统初始化');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('role_permissions::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'role_permissions::update', 'role_permissions::update',  1, '系统初始化');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('role_permissions::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'role_permissions::delete', 'role_permissions::delete',  1, '系统初始化');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('role_permissions::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'role_permissions::get', 'role_permissions::get',  1, '系统初始化');


# add user permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                           sys_created_user_name, sys_created_time, sys_update_user_id,
                           sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                           code, name, type,  description)
VALUES ('users::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::create', 'users::create',  1, '系统初始化');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                           sys_created_user_name, sys_created_time, sys_update_user_id,
                           sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                           code, name, type,  description)
VALUES ('users::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::update', 'users::update',  1, '系统初始化');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('users::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::delete', 'users::delete',  1, '系统初始化');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('users::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::get', 'users::get',  1, '系统初始化');



# add user_roles permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('user_roles::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'user_roles::create', 'user_roles::create',  1, '系统初始化');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('user_roles::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'user_roles::update', 'user_roles::update',  1, '系统初始化');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('user_roles::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'user_roles::delete', 'user_roles::delete',  1, '系统初始化');




INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('user_roles::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'user_roles::get', 'user_roles::get',  1, '系统初始化');






# add sys_configs permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_configs::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_configs::create', 'sys_configs::create',  1, '系统初始化');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_configs::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_configs::update', 'sys_configs::update',  1, '系统初始化');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_configs::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_configs::delete', 'sys_configs::delete',  1, '系统初始化');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_configs::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_configs::get', 'sys_configs::get',  1, '系统初始化');




# add sys_user_configs permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_user_configs::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_user_configs::create', 'sys_user_configs::create',  1, '系统初始化');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_user_configs::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_user_configs::update', 'sys_user_configs::update',  1, '系统初始化');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_user_configs::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_user_configs::delete', 'sys_user_configs::delete',  1, '系统初始化');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_user_configs::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_user_configs::get', 'sys_user_configs::get',  1, '系统初始化');


# add tenants permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenants::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenants::create', 'tenants::create',  1, '系统初始化');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenants::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenants::update', 'tenants::update',  1, '系统初始化');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenants::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenants::delete', 'tenants::delete',  1, '系统初始化');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenants::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenants::get', 'tenants::get',  1, '系统初始化');
