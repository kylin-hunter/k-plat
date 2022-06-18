package com.kylinhunter.plat.core.service.local;

import com.kylinhunter.plat.api.module.core.bean.entity.TenantUser;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserVO;
import com.kylinhunter.plat.api.service.local.CommonService;

/**
 * <p>
 * TenantUserService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-18
 */
public interface TenantUserService extends CommonService<TenantUser,
    TenantUserReqCreate, TenantUserReqUpdate,
    TenantUserResp, TenantUserVO, TenantUserReqQuery>  {

}
