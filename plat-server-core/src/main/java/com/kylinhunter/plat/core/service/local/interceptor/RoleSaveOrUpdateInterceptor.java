package com.kylinhunter.plat.core.service.local.interceptor;

import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.api.module.core.bean.entity.Role;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleVO;
import com.kylinhunter.plat.core.init.data.RoleInitDatas;
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
public class RoleSaveOrUpdateInterceptor extends
        SaveOrUpdateInterceptor<Role, RoleReqCreate, RoleReqUpdate, RoleResp, RoleVO, RoleReqQuery> {

    private final RoleInitDatas roleInitData;

    @Override
    public Role before(RoleReqUpdate reqUpdate, boolean tenantSupported, Role entity) {
        if (!roleInitData.canBeModified(entity.getCode())) {
            throw new ParamException("invalid  code:" + entity.getCode());
        }
        return super.before(reqUpdate, tenantSupported, entity);
    }
}
