package io.github.kylinhunter.plat.core.service.local;

import io.github.kylinhunter.plat.api.module.core.bean.entity.Permission;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionVO;
import io.github.kylinhunter.plat.api.service.local.CommonService;

/**
 * <p>
 * PermissionService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
public interface PermissionService extends CommonService<Permission,
    PermissionReqCreate, PermissionReqUpdate,
    PermissionResp, PermissionVO, PermissionReqQuery> {

}
