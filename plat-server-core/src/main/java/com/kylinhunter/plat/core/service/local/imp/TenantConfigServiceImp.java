package com.kylinhunter.plat.core.service.local.imp;

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigVO;
import com.kylinhunter.plat.core.service.local.TenantConfigService;
import com.kylinhunter.plat.core.dao.mapper.TenantConfigMapper;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * TenantConfigServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-23
 */
@Service
public class TenantConfigServiceImp
        extends CommonServiceImpl<TenantConfigMapper, TenantConfig,
        TenantConfigReqCreate, TenantConfigReqUpdate,
        TenantConfigResp, TenantConfigVO, TenantConfigReqQuery> implements TenantConfigService {

}