package com.kylinhunter.plat.core.init.initializer;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.auth.context.UserContextHandler;
import com.kylinhunter.plat.api.context.UserContext;
import com.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import com.kylinhunter.plat.api.module.core.constants.UserType;
import com.kylinhunter.plat.core.init.data.TenantInitData;
import com.kylinhunter.plat.core.service.local.TenantService;

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
public class TenantInitializer extends BasicInitializer {

    private final TenantService tenantService;
    private final TenantInitData tenantInitData;
    private final UserContextHandler userContextHandler;

    @Override
    public int order() {
        return 3;
    }

    @Override
    public void init() {

        TenantReqCreate defaultTenant = tenantInitData.getDefaultTenant();
        String code = defaultTenant.getCode();
        Tenant tenant = tenantService.queryByCode(code);
        if (tenant != null) {
            log.info("default agent exist");
            tenantInitData.addDbData(code, tenant);

        } else {
            tenantService.save(defaultTenant);
            log.info("default agent created");

            tenant = tenantService.queryByCode(code);
            tenantInitData.addDbData(code, tenant);

        }

        UserContext userContext = userContextHandler.get();
        userContext.setTenantId(tenant.getId());
        userContext.setUserType(UserType.TENANT_ADMIN.getCode());
    }

}
