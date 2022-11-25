package com.kylinhunter.plat.core.service.local.interceptor;

import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantRole;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleVO;
import com.kylinhunter.plat.core.init.data.TenantRoleInitDatas;
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
public class TenantRoleDeleteInterceptor extends
        DeleteInterceptor<TenantRole, TenantRoleReqCreate, TenantRoleReqUpdate, TenantRoleResp, TenantRoleVO, TenantRoleReqQuery> {

    private final TenantRoleInitDatas roleInitData;

    @Override
    public void before(ReqDelete reqDelete, boolean tenantSupported, TenantRole entity) {
        super.before(reqDelete, tenantSupported, entity);
        if (!roleInitData.canBeDeleted(entity.getCode())) {
            throw new ParamException("can't delete ,for  code:" + entity.getCode());
        }
    }

}
