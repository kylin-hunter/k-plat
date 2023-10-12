package io.github.kylinhunter.plat.core.service.local.imp;

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantRolePermission;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRolePermissionReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRolePermissionReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRolePermissionReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRolePermissionResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRolePermissionVO;
import io.github.kylinhunter.plat.core.service.local.TenantRolePermissionService;
import io.github.kylinhunter.plat.core.dao.mapper.TenantRolePermissionMapper;
import io.github.kylinhunter.plat.core.service.local.interceptor.TenantRolePermissionSaveOrUpdateInterceptor;
import io.github.kylinhunter.plat.core.service.local.interceptor.TenantRolePermissionDeleteInterceptor;

import org.springframework.stereotype.Service;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;

/**
 * <p>
 * TenantRolePermissionServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-10
 */
@Service
public class TenantRolePermissionServiceImp
        extends CommonServiceImpl<TenantRolePermissionMapper, TenantRolePermission,
        TenantRolePermissionReqCreate, TenantRolePermissionReqUpdate,
        TenantRolePermissionResp, TenantRolePermissionVO, TenantRolePermissionReqQuery> implements TenantRolePermissionService {

    public TenantRolePermissionServiceImp(TenantRolePermissionSaveOrUpdateInterceptor saveOrUpdateInterceptor,
        TenantRolePermissionDeleteInterceptor deleteInterceptor) {
        this.saveOrUpdateInterceptor = saveOrUpdateInterceptor;
        this.deleteInterceptor = deleteInterceptor;
    }



}