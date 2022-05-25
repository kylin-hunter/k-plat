package com.kylinhunter.plat.core.service.local.imp;

import com.kylinhunter.plat.api.module.core.bean.entity.KpRole;
import com.kylinhunter.plat.api.module.core.bean.vo.KpRoleReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.KpRoleReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.KpRoleReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.KpRoleResp;
import com.kylinhunter.plat.core.service.local.KpRoleService;
import com.kylinhunter.plat.core.dao.mapper.KpRoleMapper;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * KpRoleServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-05-26
 */
@Service
public class KpRoleServiceImp
        extends CommonServiceImpl<KpRoleMapper, KpRole,
        KpRoleReqCreate, KpRoleReqUpdate,
        KpRoleResp, KpRoleReqQuery> implements KpRoleService {

}