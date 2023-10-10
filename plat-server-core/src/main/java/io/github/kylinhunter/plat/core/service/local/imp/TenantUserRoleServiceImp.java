package io.github.kylinhunter.plat.core.service.local.imp;

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUserRole;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserRoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserRoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserRoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserRoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserRoleVO;
import io.github.kylinhunter.plat.core.service.local.TenantUserRoleService;
import io.github.kylinhunter.plat.core.dao.mapper.TenantUserRoleMapper;
import io.github.kylinhunter.plat.core.service.local.interceptor.TenantUserRoleSaveOrUpdateInterceptor;
import io.github.kylinhunter.plat.core.service.local.interceptor.TenantUserRoleDeleteInterceptor;

import org.springframework.stereotype.Service;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;

/**
 * <p>
 * TenantUserRoleServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-10
 */
@Service
public class TenantUserRoleServiceImp
        extends CommonServiceImpl<TenantUserRoleMapper, TenantUserRole,
        TenantUserRoleReqCreate, TenantUserRoleReqUpdate,
        TenantUserRoleResp, TenantUserRoleVO, TenantUserRoleReqQuery> implements TenantUserRoleService {

    public TenantUserRoleServiceImp(TenantUserRoleSaveOrUpdateInterceptor saveOrUpdateInterceptor,
        TenantUserRoleDeleteInterceptor deleteInterceptor) {
        this.saveOrUpdateInterceptor = saveOrUpdateInterceptor;
        this.deleteInterceptor = deleteInterceptor;
    }



}