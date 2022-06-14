package com.kylinhunter.plat.core.init;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import com.kylinhunter.plat.api.module.core.bean.entity.User;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqCreate;
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
public class TenantUserInitializer implements Initializer {

    private final TenantService tenantService;
    private final TenantInitDatas tenantInitDatas;

    private final UserService userService;
    private final UserInitDatas userInitDatas;

    private final TenantUserService tenantUserService;

    @Override
    public void init() {

        Tenant defaultTenant = tenantInitDatas.getDbData(TenantInitDatas.DEFAULT_CODE);
        User user = userInitDatas.getDbData(UserInitDatas.USER_TEST_CODE);

        final String tenantId = defaultTenant.getId();
        final String userId = user.getId();
        if (!tenantUserService.hasPermission(tenantId, userId)) {
            TenantUserReqCreate tenantUserReqCreate = new TenantUserReqCreate();
            tenantUserReqCreate.setTenantId(tenantId);
            tenantUserReqCreate.setUserId(userId);
            tenantUserReqCreate.setStatus(0);
            tenantUserService.save(tenantUserReqCreate);
            log.info("create permission tenantId={},user={}", defaultTenant.getCode(), user.getUserCode());

        } else {
            log.info("exist  permission tenantId={},user={}", defaultTenant.getCode(), user.getUserCode());
        }

    }

}
