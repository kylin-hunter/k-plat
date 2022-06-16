package com.kylinhunter.plat.core.service.local.imp;

import com.kylinhunter.plat.api.module.core.bean.entity.TenantRole;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleVO;
import com.kylinhunter.plat.core.service.local.TenantRoleService;
import com.kylinhunter.plat.core.dao.mapper.TenantRoleMapper;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * TenantRoleServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-17
 */
@Service
public class TenantRoleServiceImp
        extends CommonServiceImpl<TenantRoleMapper, TenantRole,
        TenantRoleReqCreate, TenantRoleReqUpdate,
        TenantRoleResp, TenantRoleVO, TenantRoleReqQuery> implements TenantRoleService {

}