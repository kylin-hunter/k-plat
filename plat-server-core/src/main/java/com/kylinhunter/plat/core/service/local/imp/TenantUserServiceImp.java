package com.kylinhunter.plat.core.service.local.imp;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUser;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserVO;
import com.kylinhunter.plat.core.dao.mapper.TenantUserMapper;
import com.kylinhunter.plat.core.service.local.TenantUserService;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;

/**
 * <p>
 * TenantUserServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-15
 */
@Service
public class TenantUserServiceImp
        extends CommonServiceImpl<TenantUserMapper, TenantUser,
        TenantUserReqCreate, TenantUserReqUpdate,
        TenantUserResp, TenantUserVO, TenantUserReqQuery> implements TenantUserService {

    @Override
    public TenantUser findByTenantAndUser(String tenantId, String userId) {

        LambdaQueryWrapper<TenantUser> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TenantUser::getSysDeleteFlag, false);
        queryWrapper.eq(TenantUser::getTenantId, tenantId);
        queryWrapper.eq(TenantUser::getUserId, userId);
        return this.baseMapper.selectOne(queryWrapper);
    }
}