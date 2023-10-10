package io.github.kylinhunter.plat.core.service.local.interceptor;

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantRolePermission;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRolePermissionReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRolePermissionReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRolePermissionReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRolePermissionResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRolePermissionVO;
import org.springframework.stereotype.Component;
import io.github.kylinhunter.plat.dao.service.local.interceptor.DeleteInterceptor;



/**
 * <p>
 * TenantRolePermissionDeleteInterceptor 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-10
 */
@Component
public class TenantRolePermissionDeleteInterceptor extends DeleteInterceptor<TenantRolePermission,
    TenantRolePermissionReqCreate, TenantRolePermissionReqUpdate,
    TenantRolePermissionResp, TenantRolePermissionVO, TenantRolePermissionReqQuery>  {

}
