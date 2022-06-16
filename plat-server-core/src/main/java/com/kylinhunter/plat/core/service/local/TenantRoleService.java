package com.kylinhunter.plat.core.service.local;

import com.kylinhunter.plat.api.module.core.bean.entity.TenantRole;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleVO;
import com.kylinhunter.plat.api.service.local.CommonService;

/**
 * <p>
 * TenantRoleService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-17
 */
public interface TenantRoleService extends CommonService<TenantRole,
        TenantRoleReqCreate, TenantRoleReqUpdate,
        TenantRoleResp, TenantRoleVO, TenantRoleReqQuery> {

    TenantRole queryByCode(String code);

}
