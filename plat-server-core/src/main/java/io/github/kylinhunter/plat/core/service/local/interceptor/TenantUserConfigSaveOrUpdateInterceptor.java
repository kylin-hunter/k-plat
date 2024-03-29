package io.github.kylinhunter.plat.core.service.local.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.api.context.UserContext;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUserConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigVO;
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
