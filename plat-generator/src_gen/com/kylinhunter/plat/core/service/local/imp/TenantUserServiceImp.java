package com.kylinhunter.plat.core.service.local.imp;

import com.kylinhunter.plat.api.module.core.bean.entity.TenantUser;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserVO;
import com.kylinhunter.plat.core.service.local.TenantUserService;
import com.kylinhunter.plat.core.dao.mapper.TenantUserMapper;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * TenantUserServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-14
 */
@Service
public class TenantUserServiceImp
        extends CommonServiceImpl<TenantUserMapper, TenantUser,
        TenantUserReqCreate, TenantUserReqUpdate,
        TenantUserResp, TenantUserVO, TenantUserReqQuery> implements TenantUserService {

}