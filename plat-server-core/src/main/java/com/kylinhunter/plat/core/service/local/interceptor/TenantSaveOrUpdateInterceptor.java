package com.kylinhunter.plat.core.service.local.interceptor;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantVO;
import com.kylinhunter.plat.commons.exception.inner.ParamException;
import com.kylinhunter.plat.core.init.data.TenantInitData;
import com.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;

import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 17:05
 **/
@Component
@RequiredArgsConstructor
public class TenantSaveOrUpdateInterceptor extends
        SaveOrUpdateInterceptor<Tenant, TenantReqCreate, TenantReqUpdate, TenantResp, TenantVO, TenantReqQuery> {
    private final TenantInitData tenantInitData;

    @Override
    public void saveOrUpdateBefore(TenantVO vo) {

        super.saveOrUpdateBefore(vo);

    }

    @Override
    public Tenant before(TenantReqUpdate tenantReqUpdate, boolean tenantSupported, Tenant entity) {
        if (!tenantInitData.canBeModified(entity.getCode())) {
            throw new ParamException("invalid Tenant code:" + entity.getCode());
        }

        return super.before(tenantReqUpdate, tenantSupported, entity);
    }
}
