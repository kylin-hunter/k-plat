use kp;


#add tenant_catalog
INSERT INTO kp.kplat_tenant_catalog (id, sys_tenant_id, sys_auto_updated, sys_created_user_id,
                                     sys_created_user_name, sys_created_time, sys_update_user_id,
                                     sys_update_user_name, sys_update_time, sys_delete_flag,
                                     sys_op_lock, code, name, type, status, description, path,
                                     level, parent_id)
VALUES ('1', '1', CURRENT_TIMESTAMP, '1', 'admin', CURRENT_TIMESTAMP, '1', 'admin',
        CURRENT_TIMESTAMP, 0, null, 'default', 'default', 0, 0, 'default', '0', 0, '0');
