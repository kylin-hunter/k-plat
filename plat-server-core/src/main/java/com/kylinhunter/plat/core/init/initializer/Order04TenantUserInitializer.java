package com.kylinhunter.plat.core.init.initializer;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import com.kylinhunter.plat.api.module.core.bean.entity.TenantUser;
import com.kylinhunter.plat.api.module.core.bean.entity.User;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqCreate;
import com.kylinhunter.plat.api.module.core.constants.UserType;
import com.kylinhunter.plat.core.init.data.TenantInitDatas;
import com.kylinhunter.plat.core.init.data.UserInitDatas;
import com.kylinhunter.plat.core.service.local.TenantService;
import com.kylinhunter.plat.core.service.local.TenantUserService;
import com.kylinhunter.plat.core.service.local.UserService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-12 23:53
 **/
@Component
@RequiredArgsConstructor
@Slf4j
@Getter
@Setter
public class Order04TenantUserInitializer extends BasicInitializer {

    private final TenantService tenantService;
    private final TenantInitDatas tenantInitData;

    private final UserService userService;
    private final UserInitDatas userInitData;

    private final TenantUserService tenantUserService;
 
    @Override
    public void init() {

        Tenant defaultTenant = tenantInitData.getDbData(TenantInitDatas.DEFAULT_CODE);

        addTenantUser(defaultTenant, UserInitDatas.USER_TEST, UserType.TENANT_USER);
        addTenantUser(defaultTenant, UserInitDatas.USER_ADMIN, UserType.TENANT_ADMIN);

    }

    private void addTenantUser(Tenant tenant, String userCode, UserType userType) {
        User user = userInitData.getDbData(userCode);

        final String tenantId = tenant.getId();
        final String userId = user.getId();
        TenantUser tenantUser = tenantUserService.findByTenantAndUser(tenantId, userId);
        if (tenantUser == null) {
            TenantUserReqCreate tenantUserReqCreate = new TenantUserReqCreate();
            tenantUserReqCreate.setTenantId(tenantId);
            tenantUserReqCreate.setUserId(userId);
            tenantUserReqCreate.setStatus(0);
            tenantUserReqCreate.setType(userType.getCode());
            tenantUserService.save(tenantUserReqCreate);
            log.info("create tenant user tenant={},user={},type={}", tenant.getCode(), userCode, userType);
        } else {
            log.info("exist tenant user tenant={},user={},type={}", tenant.getCode(), userCode, tenantUser.getType());
        }
    }

}
