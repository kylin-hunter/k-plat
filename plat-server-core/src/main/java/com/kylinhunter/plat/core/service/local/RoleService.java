package com.kylinhunter.plat.core.service.local;

import io.github.kylinhunter.plat.api.module.core.bean.entity.Role;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleVO;
import io.github.kylinhunter.plat.api.service.local.CommonService;

/**
 * <p>
 * RoleService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-11
 */
public interface RoleService extends CommonService<Role,
        RoleReqCreate, RoleReqUpdate,
        RoleResp, RoleVO, RoleReqQuery> {
    Role queryByCode(String code);

}
