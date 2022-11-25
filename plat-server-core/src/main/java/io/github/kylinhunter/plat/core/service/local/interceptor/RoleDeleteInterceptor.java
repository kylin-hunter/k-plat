package io.github.kylinhunter.plat.core.service.local.interceptor;

import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Role;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleVO;
import io.github.kylinhunter.plat.core.init.data.RoleInitDatas;
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
public class RoleDeleteInterceptor extends
        DeleteInterceptor<Role, RoleReqCreate, RoleReqUpdate, RoleResp, RoleVO, RoleReqQuery> {

    private final RoleInitDatas roleInitData;

    @Override
    public void before(ReqDelete reqDelete, boolean tenantSupported, Role entity) {
        super.before(reqDelete, tenantSupported, entity);
        if (!roleInitData.canBeDeleted(entity.getCode())) {
            throw new ParamException("can't delete ,for  code:" + entity.getCode());
        }
    }

}
