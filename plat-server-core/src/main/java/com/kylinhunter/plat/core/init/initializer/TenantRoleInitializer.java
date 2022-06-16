package com.kylinhunter.plat.core.init.initializer;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.entity.TenantRole;
import com.kylinhunter.plat.core.init.data.TenantRoleInitData;
import com.kylinhunter.plat.core.service.local.TenantRoleService;

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
public class TenantRoleInitializer extends BasicInitializer {

    private final TenantRoleInitData tenantRoleInitData;
    private final TenantRoleService tenantRoleService;

    @Override
    public int order() {
        return 5;
    }

    @Override
    public void init() {

        tenantRoleInitData.getInitDatas().values().forEach(tenantRoleCreate -> {

            final String roleCode = tenantRoleCreate.getCode();
            final TenantRole tenantRole = tenantRoleService.queryByCode(roleCode);
            if (tenantRole != null) {
                log.info("default role {} exist", roleCode);
                tenantRoleInitData.addDbData(roleCode, tenantRole);
            } else {
                tenantRoleService.save(tenantRoleCreate);
                log.info("default role {} created", roleCode);
                tenantRoleInitData.addDbData(roleCode, tenantRoleService.queryByCode(roleCode));
            }
        });

    }

}
