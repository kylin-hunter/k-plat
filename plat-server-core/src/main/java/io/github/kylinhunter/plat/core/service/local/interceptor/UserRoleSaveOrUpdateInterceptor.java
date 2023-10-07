package io.github.kylinhunter.plat.core.service.local.interceptor;

import io.github.kylinhunter.plat.api.module.core.bean.entity.UserRole;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleVO;
import org.springframework.stereotype.Component;
import io.github.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;



/**
 * <p>
 * UserRoleSaveOrUpdateInterceptor 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
@Component
public class UserRoleSaveOrUpdateInterceptor extends SaveOrUpdateInterceptor<UserRole,
    UserRoleReqCreate, UserRoleReqUpdate,
    UserRoleResp, UserRoleVO, UserRoleReqQuery>  {

}
