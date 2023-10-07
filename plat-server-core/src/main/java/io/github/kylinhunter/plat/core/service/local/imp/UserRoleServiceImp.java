package io.github.kylinhunter.plat.core.service.local.imp;

import io.github.kylinhunter.plat.api.module.core.bean.entity.UserRole;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleVO;
import io.github.kylinhunter.plat.core.service.local.UserRoleService;
import io.github.kylinhunter.plat.core.dao.mapper.UserRoleMapper;
import io.github.kylinhunter.plat.core.service.local.interceptor.UserRoleSaveOrUpdateInterceptor;
import io.github.kylinhunter.plat.core.service.local.interceptor.UserRoleDeleteInterceptor;

import org.springframework.stereotype.Service;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;

/**
 * <p>
 * UserRoleServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
@Service
public class UserRoleServiceImp
        extends CommonServiceImpl<UserRoleMapper, UserRole,
        UserRoleReqCreate, UserRoleReqUpdate,
        UserRoleResp, UserRoleVO, UserRoleReqQuery> implements UserRoleService {

    public UserRoleServiceImp(UserRoleSaveOrUpdateInterceptor saveOrUpdateInterceptor,
        UserRoleDeleteInterceptor deleteInterceptor) {
        this.saveOrUpdateInterceptor = saveOrUpdateInterceptor;
        this.deleteInterceptor = deleteInterceptor;
    }



}