package com.kylinhunter.plat.core.init.initializer;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import com.kylinhunter.plat.api.module.core.bean.entity.User;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqCreate;
import com.kylinhunter.plat.core.init.data.TenantInitData;
import com.kylinhunter.plat.core.init.data.UserInitData;
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
public class TenantUserInitializer extends BasicInitializer {

    private final TenantService tenantService;
    private final TenantInitData tenantInitData;

    private final UserService userService;
    private final UserInitData userInitData;

    private final TenantUserService tenantUserService;

    @Override
    public int order() {
        return 4;
    }

    @Override
    public void init() {

        Tenant defaultTenant = tenantInitData.getDbData(TenantInitData.DEFAULT_CODE);
        User user = userInitData.getDbData(UserInitData.USER_TEST_CODE);

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
