package com.kylinhunter.plat.core.service.local.interceptor;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantVO;
import com.kylinhunter.plat.core.init.data.TenantInitDatas;
import com.kylinhunter.plat.dao.service.local.interceptor.DeleteInterceptor;

import io.github.kylinhunter.commons.exception.embed.ParamException;
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

    private final TenantInitDatas tenantInitData;

    @Override
    public void before(ReqDelete reqDelete, boolean tenantSupported, Tenant entity) {
        super.before(reqDelete, tenantSupported, entity);
        if (!tenantInitData.canBeDeleted(entity.getCode())) {
            throw new ParamException("can't delete ,for tenant code:" + entity.getCode());
        }
    }

}
