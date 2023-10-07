package io.github.kylinhunter.plat.core.service.local.interceptor;

import io.github.kylinhunter.plat.api.module.core.bean.entity.Permission;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionVO;
import io.github.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;
import org.springframework.stereotype.Component;


/**
 * <p>
 * PermissionSaveOrUpdateInterceptor 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
@Component
public class PermissionSaveOrUpdateInterceptor extends SaveOrUpdateInterceptor<Permission,
    PermissionReqCreate, PermissionReqUpdate,
    PermissionResp, PermissionVO, PermissionReqQuery> {

}
