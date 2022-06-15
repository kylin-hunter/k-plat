package com.kylinhunter.plat.core.init.data;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.entity.Role;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleReqCreate;

import lombok.Getter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 02:03
 **/
@Component
@Getter
public class RoleInitData extends BasicInitData<RoleReqCreate, Role> {
    public static final String Role_ADMIN_CODE = "admin";
    public static final String Role_TEST_CODE = "test";

    private RoleReqCreate RoleAdmin = createRoleAdmin();
    private RoleReqCreate RoleTest = createRoleTest();

    private RoleReqCreate createRoleAdmin() {

        RoleReqCreate roleReqCreate = new RoleReqCreate();
        roleReqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
        roleReqCreate.setCode(Role_ADMIN_CODE);
        roleReqCreate.setName(Role_ADMIN_CODE);
        roleReqCreate.setType(0);
        roleReqCreate.setStatus(0);
        roleReqCreate.setDescription(Role_ADMIN_CODE);
        this.addInitData(roleReqCreate.getCode(), roleReqCreate);
        return roleReqCreate;
    }

    private RoleReqCreate createRoleTest() {

        RoleReqCreate roleReqCreate = new RoleReqCreate();
        roleReqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
        roleReqCreate.setCode(Role_TEST_CODE);
        roleReqCreate.setName(Role_TEST_CODE);
        roleReqCreate.setType(0);
        roleReqCreate.setStatus(0);
        roleReqCreate.setDescription(Role_TEST_CODE);
        this.addInitData(roleReqCreate.getCode(), roleReqCreate);
        return roleReqCreate;
    }

}
