package io.github.kylinhunter.plat.core.service.local.imp;

import org.springframework.stereotype.Service;

import io.github.kylinhunter.plat.api.module.core.bean.entity.SysUserConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigVO;
import io.github.kylinhunter.plat.core.dao.mapper.SysUserConfigMapper;
import io.github.kylinhunter.plat.core.service.local.SysUserConfigService;
import io.github.kylinhunter.plat.core.service.local.interceptor.SysUserConfigSaveOrUpdateInterceptor;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;

/**
 * <p>
 * SysUserConfigServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-23
 */
@Service
public class SysUserConfigServiceImp
        extends CommonServiceImpl<SysUserConfigMapper, SysUserConfig,
        SysUserConfigReqCreate, SysUserConfigReqUpdate,
        SysUserConfigResp, SysUserConfigVO, SysUserConfigReqQuery> implements SysUserConfigService {

    public SysUserConfigServiceImp(SysUserConfigSaveOrUpdateInterceptor sysUserConfigSaveOrUpdateInterceptor) {
        this.saveOrUpdateInterceptor = sysUserConfigSaveOrUpdateInterceptor;
    }
}