package com.kylinhunter.plat.core.service.local.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.api.context.UserContext;
import io.github.kylinhunter.plat.api.module.core.bean.entity.SysUserConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigVO;
import com.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;

import io.github.kylinhunter.commons.exception.embed.ParamException;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 17:05
 **/
@Component
@RequiredArgsConstructor
public class SysUserConfigSaveOrUpdateInterceptor extends
        SaveOrUpdateInterceptor<SysUserConfig, SysUserConfigReqCreate, SysUserConfigReqUpdate, SysUserConfigResp,
                SysUserConfigVO, SysUserConfigReqQuery> {

    @Override
    protected void saveOrUpdateBefore(SysUserConfigVO vo) {
        super.saveOrUpdateBefore(vo);
        final String userId = vo.getUserId();
        UserContext userContext = userContextHandler.get(true);
        if (!StringUtils.isEmpty(userId)) {
            if (!userId.equals(userContext.getUserId()) && !userContext.isSuperAdmin()) {
                throw new ParamException("not admin ");
            }
        } else {
            vo.setUserId(userContext.getUserId());
        }
    }

}
