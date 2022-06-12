package com.kylinhunter.plat.core.service.local;

import com.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantVO;
import com.kylinhunter.plat.api.service.local.CommonService;

/**
 * <p>
 * TenantService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-12
 */
public interface TenantService extends CommonService<Tenant,
    TenantReqCreate, TenantReqUpdate,
    TenantResp, TenantVO, TenantReqQuery>  {

}
