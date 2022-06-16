package com.kylinhunter.plat.core.service.local.interceptor;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.module.core.bean.entity.Role;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleResp;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleVO;
import com.kylinhunter.plat.commons.exception.inner.ParamException;
import com.kylinhunter.plat.core.init.data.RoleInitData;
import com.kylinhunter.plat.dao.service.local.interceptor.DeleteInterceptor;

import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 17:05
 **/
@Component
@RequiredArgsConstructor
public class RoleDeleteInterceptor extends
        DeleteInterceptor<Role, RoleReqCreate, RoleReqUpdate, RoleResp, RoleVO, RoleReqQuery> {

    private final RoleInitData roleInitData;

    @Override
    public void before(ReqDelete reqDelete, boolean tenantSupported, Role entity) {
        super.before(reqDelete, tenantSupported, entity);
        if (!roleInitData.canBeDeleted(entity.getCode())) {
            throw new ParamException("can't delete ,for  code:" + entity.getCode());
        }
    }

}
