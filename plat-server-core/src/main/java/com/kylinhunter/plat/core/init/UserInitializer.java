package com.kylinhunter.plat.core.init;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import com.kylinhunter.plat.core.service.local.UserService;

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
public class UserInitializer implements Initializer {

    private static final String DEFAULT_CODE = "admin";
    private static final String DEFAULT_NAME = "admin";

    private final UserService userService;

    @Override
    public void init() {

        TenantReqCreate tenantReqCreate = getDefaultTenant();
        if (userService.queryByUserCode(DEFAULT_CODE) != null) {
            log.info("default agent exist");
        } else {
            userService.save(tenantReqCreate);
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
