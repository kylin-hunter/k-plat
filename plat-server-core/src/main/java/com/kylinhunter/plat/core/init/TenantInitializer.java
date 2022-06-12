package com.kylinhunter.plat.core.init;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
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
public class TenantInitializer implements Initializer {

    private final TenantService tenantService;
    private final TenantInitDatas tenantInitDatas;

    @Override
    public void init() {

        TenantReqCreate defaultTenant = tenantInitDatas.getDefaultTenant();
        if (tenantService.queryByCode(defaultTenant.getCode()) != null) {
            log.info("default agent exist");
        } else {
            tenantService.save(defaultTenant);
            log.info("default agent created");
        }
    }

}
