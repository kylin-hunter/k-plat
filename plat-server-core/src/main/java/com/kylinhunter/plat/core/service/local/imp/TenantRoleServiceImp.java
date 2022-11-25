package com.kylinhunter.plat.core.service.local.imp;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantRole;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleVO;
import com.kylinhunter.plat.core.dao.mapper.TenantRoleMapper;
import com.kylinhunter.plat.core.service.local.TenantRoleService;
import com.kylinhunter.plat.core.service.local.interceptor.TenantRoleDeleteInterceptor;
import com.kylinhunter.plat.core.service.local.interceptor.TenantRoleSaveOrUpdateInterceptor;
import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;

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

    public TenantRoleServiceImp(TenantRoleSaveOrUpdateInterceptor tenantRoleSaveOrUpdateInterceptor,
                                TenantRoleDeleteInterceptor tenantRoleDeleteInterceptor) {
        this.saveOrUpdateInterceptor = tenantRoleSaveOrUpdateInterceptor;
        this.deleteInterceptor = tenantRoleDeleteInterceptor;
    }

    @Override
    public TenantRole queryByCode(String code) {
        LambdaQueryWrapper<TenantRole> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(TenantRole::getSysDeleteFlag, false);
        queryWrapper.eq(TenantRole::getCode, code);
        return this.baseMapper.selectOne(queryWrapper);
    }
}