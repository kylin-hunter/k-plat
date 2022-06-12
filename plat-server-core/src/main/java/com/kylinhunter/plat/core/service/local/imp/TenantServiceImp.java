package com.kylinhunter.plat.core.service.local.imp;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantVO;
import com.kylinhunter.plat.core.dao.mapper.TenantMapper;
import com.kylinhunter.plat.core.service.local.TenantService;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;

/**
 * <p>
 * TenantServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-12
 */
@Service
public class TenantServiceImp
        extends CommonServiceImpl<TenantMapper, Tenant,
        TenantReqCreate, TenantReqUpdate,
        TenantResp, TenantVO, TenantReqQuery> implements TenantService {

    @Override
    public Tenant queryByCode(String code) {
        LambdaQueryWrapper<Tenant> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Tenant::getSysDeleteFlag, false);
        queryWrapper.eq(Tenant::getCode, code);
        return this.baseMapper.selectOne(queryWrapper);
    }
}