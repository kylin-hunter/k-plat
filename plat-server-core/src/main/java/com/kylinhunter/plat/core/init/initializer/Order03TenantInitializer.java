package com.kylinhunter.plat.core.init.initializer;

import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.api.auth.context.UserContextHandler;
import io.github.kylinhunter.plat.api.context.UserContext;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import io.github.kylinhunter.plat.api.module.core.constants.UserType;
import com.kylinhunter.plat.core.init.data.TenantInitDatas;
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
public class Order03TenantInitializer extends BasicInitializer {

    private final TenantService tenantService;
    private final TenantInitDatas tenantInitData;
    private final UserContextHandler userContextHandler;


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
