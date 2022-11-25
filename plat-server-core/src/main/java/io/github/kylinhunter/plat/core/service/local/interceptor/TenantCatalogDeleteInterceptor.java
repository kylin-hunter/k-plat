package io.github.kylinhunter.plat.core.service.local.interceptor;

import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogVO;
import io.github.kylinhunter.plat.core.init.data.TenantCatalogInitDatas;
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
