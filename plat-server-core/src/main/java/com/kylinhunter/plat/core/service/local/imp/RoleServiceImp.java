package com.kylinhunter.plat.core.service.local.imp;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kylinhunter.plat.api.module.core.bean.entity.Role;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleResp;
import com.kylinhunter.plat.api.module.core.bean.vo.RoleVO;
import com.kylinhunter.plat.core.dao.mapper.RoleMapper;
import com.kylinhunter.plat.core.service.local.RoleService;
import com.kylinhunter.plat.core.service.local.interceptor.RoleDeleteInterceptor;
import com.kylinhunter.plat.core.service.local.interceptor.RoleSaveOrUpdateInterceptor;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;

/**
 * <p>
 * RoleServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-11
 */
@Service
public class RoleServiceImp
        extends CommonServiceImpl<RoleMapper, Role,
        RoleReqCreate, RoleReqUpdate,
        RoleResp, RoleVO, RoleReqQuery> implements RoleService {

    public RoleServiceImp(RoleSaveOrUpdateInterceptor RoleSaveOrUpdateInterceptor,
                          RoleDeleteInterceptor RoleDeleteInterceptor) {
        this.saveOrUpdateInterceptor = RoleSaveOrUpdateInterceptor;
        this.deleteInterceptor = RoleDeleteInterceptor;
        this.tenantSupported = false;
    }

    @Override
    public Role queryByCode(String code) {
        LambdaQueryWrapper<Role> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Role::getSysDeleteFlag, false);
        queryWrapper.eq(Role::getCode, code);
        return this.baseMapper.selectOne(queryWrapper);
    }
}