package io.github.kylinhunter.plat.core.service.local;

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUser;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserVO;
import io.github.kylinhunter.plat.api.service.local.CommonService;

/**
 * <p>
 * TenantUserService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-15
 */
public interface TenantUserService extends CommonService<TenantUser,
        TenantUserReqCreate, TenantUserReqUpdate,
        TenantUserResp, TenantUserVO, TenantUserReqQuery> {

    TenantUser findByTenantAndUser(String tenantId, String userId);
}
