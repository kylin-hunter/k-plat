package io.github.kylinhunter.plat.core.service.local.interceptor;

import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantVO;
import io.github.kylinhunter.plat.core.init.data.TenantInitDatas;
import io.github.kylinhunter.plat.dao.service.local.interceptor.DeleteInterceptor;

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
