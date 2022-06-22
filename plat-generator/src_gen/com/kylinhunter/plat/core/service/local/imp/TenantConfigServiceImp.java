package com.kylinhunter.plat.core.service.local.imp;

import com.kylinhunter.plat.api.module.core.bean.entity.TenantConfig;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantConfigResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantConfigVO;
import com.kylinhunter.plat.core.service.local.TenantConfigService;
import com.kylinhunter.plat.core.dao.mapper.TenantConfigMapper;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;

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