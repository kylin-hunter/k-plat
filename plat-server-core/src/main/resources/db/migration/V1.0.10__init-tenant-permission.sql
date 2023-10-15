use kp;


# add tenant_users permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_users::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_users::create', 'tenant_users::create', 1,
        '系统初始化'),

       ('tenant_users::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_users::update', 'tenant_users::update', 1,
        '系统初始化'),

       ('tenant_users::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_users::delete', 'tenant_users::delete', 1,
        '系统初始化'),
       ('tenant_users::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_users::get', 'tenant_users::get', 1, '系统初始化');


# add tenant_roles permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_roles::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_roles::create', 'tenant_roles::create', 1,
        '系统初始化');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_roles::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_roles::update', 'tenant_roles::update', 1,
        '系统初始化');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_roles::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_roles::delete', 'tenant_roles::delete', 1,
        '系统初始化');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_roles::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_roles::get', 'tenant_roles::get', 1, '系统初始化');


# add tenant_catalogs permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_configs::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_configs::create', 'tenant_configs::create', 1,
        '系统初始化');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_configs::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_configs::update', 'tenant_configs::update', 1,
        '系统初始化');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_configs::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_configs::delete', 'tenant_configs::delete', 1,
        '系统初始化');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_configs::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_configs::get', 'tenant_configs::get', 1, '系统初始化');


# add tenant_user_configs permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_user_configs::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_user_configs::create', 'tenant_user_configs::create', 1,
        '系统初始化');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_user_configs::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_user_configs::update', 'tenant_user_configs::update', 1,
        '系统初始化');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_user_configs::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_user_configs::delete', 'tenant_user_configs::delete', 1,
        '系统初始化');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_user_configs::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_user_configs::get', 'tenant_user_configs::get', 1,
        '系统初始化');


# add tenant_catalogs permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_catalogs::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_catalogs::create', 'tenant_catalogs::create', 1,
        '系统初始化');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_catalogs::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_catalogs::update', 'tenant_catalogs::update', 1,
        '系统初始化');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_catalogs::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_catalogs::delete', 'tenant_catalogs::delete', 1,
        '系统初始化');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag,
                                 sys_op_lock,
                                 code, name, type, description)
VALUES ('tenant_catalogs::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1',
        'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_catalogs::get', 'tenant_catalogs::get', 1,
        '系统初始化');


