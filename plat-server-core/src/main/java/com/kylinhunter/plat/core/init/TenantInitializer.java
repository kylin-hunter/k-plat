package com.kylinhunter.plat.core.init;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import com.kylinhunter.plat.core.service.local.TenantService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-12 23:53
 **/
@Component
@RequiredArgsConstructor
@Slf4j
public class TenantInitializer implements Initializer {

    private static final String DEFAULT_CODE = "default";
    private static final String DEFAULT_NAME = "DEFAULT";

    private final TenantService tenantService;

    @Override
    public void init() {

        TenantReqCreate tenantReqCreate = getDefaultTenant();
        if (tenantService.queryByCode(DEFAULT_CODE) != null) {
            log.info("default agent exist");
        } else {
            tenantService.save(tenantReqCreate);
            log.info("default agent created");
        }
    }

    public TenantReqCreate getDefaultTenant() {

        TenantReqCreate tenant = new TenantReqCreate();
        tenant.setCode(DEFAULT_CODE);
        tenant.setName(DEFAULT_NAME);
        tenant.setType(1);
        tenant.setStatus(0);
        tenant.setDescription("默认租户");

        return tenant;
    }
}
