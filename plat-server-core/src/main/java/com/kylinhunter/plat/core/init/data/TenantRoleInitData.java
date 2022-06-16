package com.kylinhunter.plat.core.init.data;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.entity.TenantRole;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqCreate;

import lombok.Getter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 02:03
 **/
@Component
@Getter
public class TenantRoleInitData extends BasicInitData<TenantRoleReqCreate, TenantRole> {
    public static final String ROLE_ADMIN_CODE = "tenant_admin";
    public static final String ROLE_TEST_CODE = "tenant_test";

    private TenantRoleReqCreate roleAdmin = createRoleAdmin();
    private TenantRoleReqCreate roleTest = createRoleTest();

    private TenantRoleReqCreate createRoleAdmin() {

        TenantRoleReqCreate roleReqCreate = new TenantRoleReqCreate();
        roleReqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
        roleReqCreate.setCode(ROLE_ADMIN_CODE);
        roleReqCreate.setName(ROLE_ADMIN_CODE);
        roleReqCreate.setType(0);
        roleReqCreate.setStatus(0);
        roleReqCreate.setDescription(ROLE_ADMIN_CODE);
        this.addInitData(roleReqCreate.getCode(), roleReqCreate);
        return roleReqCreate;
    }

    private TenantRoleReqCreate createRoleTest() {

        TenantRoleReqCreate roleReqCreate = new TenantRoleReqCreate();
        roleReqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
        roleReqCreate.setCode(ROLE_TEST_CODE);
        roleReqCreate.setName(ROLE_TEST_CODE);
        roleReqCreate.setType(0);
        roleReqCreate.setStatus(0);
        roleReqCreate.setDescription(ROLE_TEST_CODE);
        this.addInitData(roleReqCreate.getCode(), roleReqCreate);
        return roleReqCreate;
    }

}
