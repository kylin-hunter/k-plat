package com.kylinhunter.plat.core.service.local.interceptor;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantVO;
import com.kylinhunter.plat.commons.exception.inner.ParamException;
import com.kylinhunter.plat.core.init.TenantInitDatas;
import com.kylinhunter.plat.dao.service.local.interceptor.DeleteInterceptor;

import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 17:05
 **/
@Component
@RequiredArgsConstructor
public class TenantDeleteInterceptor extends
        DeleteInterceptor<Tenant, TenantReqCreate, TenantReqUpdate, TenantResp, TenantVO, TenantReqQuery> {

    private final TenantInitDatas tenantInitDatas;

    @Override
    public void before(ReqDelete reqDelete, Tenant entity) {
        super.before(reqDelete, entity);
        if (!tenantInitDatas.canBeDeleted(entity.getCode())) {
            throw new ParamException("can't delete ,for tenant code:" + entity.getCode());
        }
    }

}
