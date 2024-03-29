package io.github.kylinhunter.plat.core.service.local.interceptor;

import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantVO;
import io.github.kylinhunter.plat.core.init.data.TenantInitDatas;
import io.github.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;

import io.github.kylinhunter.commons.exception.embed.ParamException;
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
    private final TenantInitDatas tenantInitData;

    @Override
    public void saveOrUpdateBefore(TenantVO vo) {

        super.saveOrUpdateBefore(vo);

    }

    @Override
    public Tenant before(TenantReqUpdate reqUpdate, boolean tenantSupported, Tenant entity) {
        if (!tenantInitData.canBeModified(entity.getCode())) {
            throw new ParamException("invalid Tenant code:" + entity.getCode());
        }

        return super.before(reqUpdate, tenantSupported, entity);
    }
}
