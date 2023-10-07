use kp;

# add user permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                           sys_created_user_name, sys_created_time, sys_update_user_id,
                           sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                           code, name, type,  description)
VALUES ('101', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::create', 'users::create',  1, 'admin');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                           sys_created_user_name, sys_created_time, sys_update_user_id,
                           sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                           code, name, type,  description)
VALUES ('102', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::update', 'users::update',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('103', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::delete', 'users::delete',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('104', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::batch_delete', 'users::batch_delete',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('105', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::get', 'users::get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('106', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::batch_get', 'users::batch_get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('107', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'users::list', 'users::list',  1, 'admin');


# add role permission

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('201', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::create', 'roles::create',  1, 'admin');

INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('202', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::update', 'roles::update',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('203', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::delete', 'roles::delete',  1, 'admin');


INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('204', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::batch_delete', 'roles::batch_delete',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('205', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::get', 'roles::get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('206', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::batch_get', 'roles::batch_get',  1, 'admin');



INSERT INTO kp.kplat_permission (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                 sys_created_user_name, sys_created_time, sys_update_user_id,
                                 sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                                 code, name, type,  description)
VALUES ('207', '', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'roles::list', 'roles::list',  1, 'admin');




