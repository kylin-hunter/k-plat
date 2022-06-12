package com.kylinhunter.plat.core.init;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;

import lombok.Getter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 02:03
 **/
@Component
@Getter
public class TenantInitDatas implements InitData {
    private static final String DEFAULT_CODE = "default";
    private static final String DEFAULT_NAME = "DEFAULT";
    private TenantReqCreate defaultTenant = createDefaultTenant();

    private TenantReqCreate createDefaultTenant() {

        TenantReqCreate tenant = new TenantReqCreate();
        tenant.setCode(DEFAULT_CODE);
        tenant.setName(DEFAULT_NAME);
        tenant.setType(1);
        tenant.setStatus(0);
        tenant.setDescription("默认租户");
        return tenant;
    }

    @Override
    public boolean canBeModified(String code) {
        if (DEFAULT_CODE.equalsIgnoreCase(code)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean canBeDeleted(String code) {
        if (DEFAULT_CODE.equalsIgnoreCase(code)) {
            return false;
        }
        return true;
    }

}
