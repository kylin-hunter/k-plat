package io.github.kylinhunter.plat.core.service.local.imp;

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUserConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigVO;
import io.github.kylinhunter.plat.core.service.local.TenantUserConfigService;
import io.github.kylinhunter.plat.core.dao.mapper.TenantUserConfigMapper;
import io.github.kylinhunter.plat.core.service.local.interceptor.TenantUserConfigSaveOrUpdateInterceptor;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * TenantUserConfigServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-23
 */
@Service
public class TenantUserConfigServiceImp
        extends CommonServiceImpl<TenantUserConfigMapper, TenantUserConfig,
        TenantUserConfigReqCreate, TenantUserConfigReqUpdate,
        TenantUserConfigResp, TenantUserConfigVO, TenantUserConfigReqQuery> implements TenantUserConfigService {

    public TenantUserConfigServiceImp(TenantUserConfigSaveOrUpdateInterceptor tenantUserConfigSaveOrUpdateInterceptor) {
        this.saveOrUpdateInterceptor = tenantUserConfigSaveOrUpdateInterceptor;
    }
}