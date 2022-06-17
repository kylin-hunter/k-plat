package com.kylinhunter.plat.core.init.data;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import com.kylinhunter.plat.api.module.core.constants.TenantType;

import lombok.Getter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 02:03
 **/
@Component
@Getter
public class TenantInitDatas extends BasicInitDatas<TenantReqCreate, Tenant> {
    public static final String DEFAULT_CODE = "default";
    public static final String DEFAULT_NAME = "DEFAULT";
    private TenantReqCreate defaultTenant = createDefaultTenant();

    private TenantReqCreate createDefaultTenant() {

        TenantReqCreate tenant = new TenantReqCreate();
        tenant.setCode(DEFAULT_CODE);
        tenant.setName(DEFAULT_NAME);
        tenant.setType(TenantType.SYS.getCode());
        tenant.setStatus(0);
        tenant.setDescription("默认租户");
        this.addInitData(tenant.getCode(), tenant);
        return tenant;
    }

}
