use kp;
# add user
INSERT INTO kp.kplat_user (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                           sys_created_user_name, sys_created_time, sys_update_user_id,
                           sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                           user_code, user_name, password, source, type, status, description)
VALUES ('1', '', CURRENT_TIMESTAMP, '0', '', CURRENT_TIMESTAMP, '0', '', CURRENT_TIMESTAMP, 0, null,
        'admin', 'admin', '$2a$10$P/zu2zHwc0Pwf2Ma1yaKjO1FGMTa0dZa8wfJVUYZUgLFgGpXACVly', '0', 1, 0,
        'admin');

INSERT INTO kp.kplat_user (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                           sys_created_user_name, sys_created_time, sys_update_user_id,
                           sys_update_user_name, sys_update_time, sys_delete_flag, sys_op_lock,
                           user_code, user_name, password, source, type, status, description)
VALUES ('2', '', CURRENT_TIMESTAMP, '0', '', CURRENT_TIMESTAMP, '0', '', CURRENT_TIMESTAMP, 0, null,
        'test', 'test', '$2a$10$HfkOihQbjkLpw1qq/hz8k.KEMbAuy0UE.qL.cEjGdcmz7OfvKV9Ci', '0', 0, 0,
        'test');
