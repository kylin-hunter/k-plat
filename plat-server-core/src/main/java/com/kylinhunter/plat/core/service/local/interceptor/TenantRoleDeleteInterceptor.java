package com.kylinhunter.plat.core.service.local.interceptor;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.module.core.bean.entity.TenantRole;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleVO;
import com.kylinhunter.plat.commons.exception.inner.ParamException;
import com.kylinhunter.plat.core.init.data.TenantRoleInitData;
import com.kylinhunter.plat.dao.service.local.interceptor.DeleteInterceptor;

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

    private final TenantRoleInitData roleInitData;

    @Override
    public void before(ReqDelete reqDelete, boolean tenantSupported, TenantRole entity) {
        super.before(reqDelete, tenantSupported, entity);
        if (!roleInitData.canBeDeleted(entity.getCode())) {
            throw new ParamException("can't delete ,for  code:" + entity.getCode());
        }
    }

}
