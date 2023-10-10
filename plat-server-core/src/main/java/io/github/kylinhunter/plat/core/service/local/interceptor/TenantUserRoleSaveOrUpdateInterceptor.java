package io.github.kylinhunter.plat.core.service.local.interceptor;

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUserRole;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserRoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserRoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserRoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserRoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserRoleVO;
import org.springframework.stereotype.Component;
import io.github.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;



/**
 * <p>
 * TenantUserRoleSaveOrUpdateInterceptor 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-10
 */
@Component
public class TenantUserRoleSaveOrUpdateInterceptor extends SaveOrUpdateInterceptor<TenantUserRole,
    TenantUserRoleReqCreate, TenantUserRoleReqUpdate,
    TenantUserRoleResp, TenantUserRoleVO, TenantUserRoleReqQuery>  {

}
