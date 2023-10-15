use kp;
# add user
INSERT INTO kp.kplat_user (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                           sys_created_user_name, sys_created_time, sys_update_user_id,
                           sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                           user_name, nick_name, real_name, password, source, type, status,
                           description)
VALUES ('1', '', CURRENT_TIMESTAMP, '0', '', CURRENT_TIMESTAMP, '0', '', CURRENT_TIMESTAMP, 0, null,
        'admin', '管理员昵称', '管理员',
        '$2a$10$P/zu2zHwc0Pwf2Ma1yaKjO1FGMTa0dZa8wfJVUYZUgLFgGpXACVly', '0', 100,
        0,
        '系统初始化');

INSERT INTO kp.kplat_user (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                           sys_created_user_name, sys_created_time, sys_update_user_id,
                           sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                           user_name, nick_name, real_name, password, source, type, status,
                           description)
VALUES ('101', '', CURRENT_TIMESTAMP, '0', '', CURRENT_TIMESTAMP, '0', '', CURRENT_TIMESTAMP, 0, null,
        'user1', '测试用户2昵称', '测试用户1',
        '$2a$10$70ijwweqymjZIGvVPlcTxOfKUcSoNIaSqmWmTjGxWbpfe74RUDEFe', '0', 0, 0, '系统初始化');
INSERT INTO kp.kplat_user (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                           sys_created_user_name, sys_created_time, sys_update_user_id,
                           sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                           user_name, nick_name, real_name, password, source, type, status,
                           description)
VALUES ('102', '', CURRENT_TIMESTAMP, '0', '', CURRENT_TIMESTAMP, '0', '', CURRENT_TIMESTAMP, 0, null,
        'user2', '测试用户2昵称', '测试用户2',
        '$2a$10$Gk.NdzVrzYa2axRylSlGme4K8xdBnTuelNppD4zLg.lOVwyfcV33q', '0', 0, 0, '系统初始化');
