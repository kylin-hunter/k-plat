package io.github.kylinhunter.plat.core.service.local;

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigVO;
import io.github.kylinhunter.plat.api.service.local.CommonService;

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
