package com.kylinhunter.plat.core.service.local;

import com.kylinhunter.plat.api.module.core.bean.entity.TenantConfig;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantConfigResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantConfigVO;
import com.kylinhunter.plat.api.service.local.CommonService;

/**
 * <p>
 * TenantConfigService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-23
 */
public interface TenantConfigService extends CommonService<TenantConfig,
    TenantConfigReqCreate, TenantConfigReqUpdate,
    TenantConfigResp, TenantConfigVO, TenantConfigReqQuery>  {

}
