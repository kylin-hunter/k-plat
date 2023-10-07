use kp;

# add user permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                           sys_created_user_name, sys_created_time, sys_update_user_id,
                           sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                           code, name, type,  description)
VALUES ('users::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::create', 'users::create',  1, 'admin');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                           sys_created_user_name, sys_created_time, sys_update_user_id,
                           sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                           code, name, type,  description)
VALUES ('users::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::update', 'users::update',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('users::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::delete', 'users::delete',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('users::batch_delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::batch_delete', 'users::batch_delete',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('users::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::get', 'users::get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('users::batch_get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::batch_get', 'users::batch_get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('users::list', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::list', 'users::list',  1, 'admin');


# add role permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('roles::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::create', 'roles::create',  1, 'admin');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('roles::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::update', 'roles::update',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('roles::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::delete', 'roles::delete',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('roles::batch_delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::batch_delete', 'roles::batch_delete',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('roles::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::get', 'roles::get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('roles::batch_get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::batch_get', 'roles::batch_get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('roles::list', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::list', 'roles::list',  1, 'admin');




# add permission permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('permissions::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'permissions::create', 'permissions::create',  1, 'admin');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('permissions::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'permissions::update', 'permissions::update',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('permissions::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'permissions::delete', 'permissions::delete',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('permissions::batch_delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'permissions::batch_delete', 'permissions::batch_delete',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('permissions::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'permissions::get', 'permissions::get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('permissions::batch_get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'permissions::batch_get', 'permissions::batch_get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('permissions::list', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'permissions::list', 'permissions::list',  1, 'admin');




# add role_permissions permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('role_permissions::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'role_permissions::create', 'role_permissions::create',  1, 'admin');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('role_permissions::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'role_permissions::update', 'role_permissions::update',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('role_permissions::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'role_permissions::delete', 'role_permissions::delete',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('role_permissions::batch_delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'role_permissions::batch_delete', 'role_permissions::batch_delete',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('role_permissions::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'role_permissions::get', 'role_permissions::get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('role_permissions::batch_get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'role_permissions::batch_get', 'role_permissions::batch_get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('role_permissions::list', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'role_permissions::list', 'role_permissions::list',  1, 'admin');




# add sys_configs permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_configs::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_configs::create', 'sys_configs::create',  1, 'admin');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_configs::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_configs::update', 'sys_configs::update',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_configs::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_configs::delete', 'sys_configs::delete',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_configs::batch_delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_configs::batch_delete', 'sys_configs::batch_delete',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_configs::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_configs::get', 'sys_configs::get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_configs::batch_get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_configs::batch_get', 'sys_configs::batch_get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_configs::list', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_configs::list', 'sys_configs::list',  1, 'admin');



# add sys_user_configs permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_user_configs::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_user_configs::create', 'sys_user_configs::create',  1, 'admin');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_user_configs::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_user_configs::update', 'sys_user_configs::update',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_user_configs::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_user_configs::delete', 'sys_user_configs::delete',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_user_configs::batch_delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_user_configs::batch_delete', 'sys_user_configs::batch_delete',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_user_configs::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_user_configs::get', 'sys_user_configs::get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_user_configs::batch_get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_user_configs::batch_get', 'sys_user_configs::batch_get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('sys_user_configs::list', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'sys_user_configs::list', 'sys_user_configs::list',  1, 'admin');




# add tenant_catalogs permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_catalogs::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_catalogs::create', 'tenant_catalogs::create',  1, 'admin');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_catalogs::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_catalogs::update', 'tenant_catalogs::update',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_catalogs::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_catalogs::delete', 'tenant_catalogs::delete',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_catalogs::batch_delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_catalogs::batch_delete', 'tenant_catalogs::batch_delete',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_catalogs::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_catalogs::get', 'tenant_catalogs::get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_catalogs::batch_get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_catalogs::batch_get', 'tenant_catalogs::batch_get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_catalogs::list', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_catalogs::list', 'tenant_catalogs::list',  1, 'admin');





# add tenant_catalogs permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_configs::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_configs::create', 'tenant_configs::create',  1, 'admin');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_configs::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_configs::update', 'tenant_configs::update',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_configs::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_configs::delete', 'tenant_configs::delete',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_configs::batch_delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_configs::batch_delete', 'tenant_configs::batch_delete',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_configs::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_configs::get', 'tenant_configs::get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_configs::batch_get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_configs::batch_get', 'tenant_configs::batch_get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_configs::list', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_configs::list', 'tenant_configs::list',  1, 'admin');



# add tenants permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenants::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenants::create', 'tenants::create',  1, 'admin');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenants::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenants::update', 'tenants::update',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenants::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenants::delete', 'tenants::delete',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenants::batch_delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenants::batch_delete', 'tenants::batch_delete',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenants::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenants::get', 'tenants::get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenants::batch_get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenants::batch_get', 'tenants::batch_get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenants::list', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenants::list', 'tenants::list',  1, 'admin');



# add tenant_roles permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_roles::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_roles::create', 'tenant_roles::create',  1, 'admin');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_roles::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_roles::update', 'tenant_roles::update',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_roles::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_roles::delete', 'tenant_roles::delete',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_roles::batch_delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_roles::batch_delete', 'tenant_roles::batch_delete',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_roles::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_roles::get', 'tenant_roles::get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_roles::batch_get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_roles::batch_get', 'tenant_roles::batch_get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_roles::list', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_roles::list', 'tenant_roles::list',  1, 'admin');




# add tenant_user_configs permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_user_configs::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_user_configs::create', 'tenant_user_configs::create',  1, 'admin');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_user_configs::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_user_configs::update', 'tenant_user_configs::update',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_user_configs::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_user_configs::delete', 'tenant_user_configs::delete',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_user_configs::batch_delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_user_configs::batch_delete', 'tenant_user_configs::batch_delete',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_user_configs::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_user_configs::get', 'tenant_user_configs::get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_user_configs::batch_get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_user_configs::batch_get', 'tenant_user_configs::batch_get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_user_configs::list', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_user_configs::list', 'tenant_user_configs::list',  1, 'admin');


# add tenant_users permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_users::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_users::create', 'tenant_users::create',  1, 'admin');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_users::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_users::update', 'tenant_users::update',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_users::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_users::delete', 'tenant_users::delete',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_users::batch_delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_users::batch_delete', 'tenant_users::batch_delete',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_users::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_users::get', 'tenant_users::get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_users::batch_get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_users::batch_get', 'tenant_users::batch_get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('tenant_users::list', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'tenant_users::list', 'tenant_users::list',  1, 'admin');





# add user_roles permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('user_roles::create', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'user_roles::create', 'user_roles::create',  1, 'admin');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('user_roles::update', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'user_roles::update', 'user_roles::update',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('user_roles::delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'user_roles::delete', 'user_roles::delete',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('user_roles::batch_delete', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'user_roles::batch_delete', 'user_roles::batch_delete',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('user_roles::get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'user_roles::get', 'user_roles::get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('user_roles::batch_get', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'user_roles::batch_get', 'user_roles::batch_get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('user_roles::list', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'user_roles::list', 'user_roles::list',  1, 'admin');





