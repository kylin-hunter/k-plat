package com.kylinhunter.plat.core.service.local.interceptor;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.entity.Role;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleResp;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleVO;
import com.kylinhunter.plat.commons.exception.inner.ParamException;
import com.kylinhunter.plat.core.init.data.RoleInitData;
import com.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;

import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 17:05
 **/
@Component
@RequiredArgsConstructor
public class RoleSaveOrUpdateInterceptor extends
        SaveOrUpdateInterceptor<Role, RoleReqCreate, RoleReqUpdate, RoleResp, RoleVO, RoleReqQuery> {

    private final RoleInitData roleInitData;

    @Override
    public Role before(RoleReqUpdate roleReqUpdate, Role entity) {
        if (!roleInitData.canBeModified(entity.getCode())) {
            throw new ParamException("invalid  code:" + entity.getCode());
        }
        return super.before(roleReqUpdate, entity);
    }
}
