package io.github.kylinhunter.plat.core.service.local.interceptor;

import io.github.kylinhunter.plat.api.module.core.bean.entity.RolePermission;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionVO;
import io.github.kylinhunter.plat.dao.service.local.interceptor.DeleteInterceptor;
import org.springframework.stereotype.Component;


/**
 * <p>
 * RolePermissionDeleteInterceptor 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
@Component
public class RolePermissionDeleteInterceptor extends DeleteInterceptor<RolePermission,
    RolePermissionReqCreate, RolePermissionReqUpdate,
    RolePermissionResp, RolePermissionVO, RolePermissionReqQuery> {

}
