package com.kylinhunter.plat.core.service.local.imp;

import com.kylinhunter.plat.api.module.core.bean.entity.Role;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleResp;
import com.kylinhunter.plat.core.service.local.RoleService;
import com.kylinhunter.plat.core.dao.mapper.RoleMapper;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * RoleServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-05-31
 */
@Service
public class RoleServiceImp
        extends CommonServiceImpl<RoleMapper, Role,
        RoleReqCreate, RoleReqUpdate,
        RoleResp, RoleReqQuery> implements RoleService {

}