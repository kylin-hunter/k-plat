package com.kylinhunter.plat.core.service.local.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.context.UserContext;
import com.kylinhunter.plat.api.module.core.bean.entity.TenantUserConfig;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigVO;
import com.kylinhunter.plat.commons.exception.inner.ParamException;
import com.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;

import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 17:05
 **/
@Component
@RequiredArgsConstructor
public class TenantUserConfigSaveOrUpdateInterceptor extends
        SaveOrUpdateInterceptor<TenantUserConfig, TenantUserConfigReqCreate, TenantUserConfigReqUpdate, TenantUserConfigResp,
                TenantUserConfigVO, TenantUserConfigReqQuery> {

    @Override
    protected void saveOrUpdateBefore(TenantUserConfigVO vo) {
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
