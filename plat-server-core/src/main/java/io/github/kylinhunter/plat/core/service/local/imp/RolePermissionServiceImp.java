package io.github.kylinhunter.plat.core.service.local.imp;

import io.github.kylinhunter.plat.api.module.core.bean.entity.RolePermission;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionVO;
import io.github.kylinhunter.plat.core.dao.mapper.RolePermissionMapper;
import io.github.kylinhunter.plat.core.service.local.RolePermissionService;
import io.github.kylinhunter.plat.core.service.local.interceptor.RolePermissionDeleteInterceptor;
import io.github.kylinhunter.plat.core.service.local.interceptor.RolePermissionSaveOrUpdateInterceptor;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * RolePermissionServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
@Service
public class RolePermissionServiceImp
        extends CommonServiceImpl<RolePermissionMapper, RolePermission,
                RolePermissionReqCreate, RolePermissionReqUpdate,
                RolePermissionResp, RolePermissionVO, RolePermissionReqQuery> implements RolePermissionService {

    public RolePermissionServiceImp(RolePermissionSaveOrUpdateInterceptor saveOrUpdateInterceptor,
        RolePermissionDeleteInterceptor deleteInterceptor) {
        this.saveOrUpdateInterceptor = saveOrUpdateInterceptor;
        this.deleteInterceptor = deleteInterceptor;
    }



}