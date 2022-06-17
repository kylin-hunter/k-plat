package com.kylinhunter.plat.core.service.local.interceptor;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogVO;
import com.kylinhunter.plat.commons.exception.inner.ParamException;
import com.kylinhunter.plat.core.init.data.TenantCatalogInitDatas;
import com.kylinhunter.plat.dao.service.local.interceptor.DeleteInterceptor;

import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 17:05
 **/
@Component
@RequiredArgsConstructor
public class TenantCatalogDeleteInterceptor extends
        DeleteInterceptor<TenantCatalog, TenantCatalogReqCreate, TenantCatalogReqUpdate, TenantCatalogResp, TenantCatalogVO, TenantCatalogReqQuery> {

    private final TenantCatalogInitDatas initData;

    @Override
    public void before(ReqDelete reqDelete, boolean tenantSupported, TenantCatalog entity) {
        super.before(reqDelete, tenantSupported, entity);
        if (!initData.canBeDeleted(entity.getCode())) {
            throw new ParamException("can't delete ,for  code:" + entity.getCode());
        }
    }

}
