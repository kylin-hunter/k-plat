package com.kylinhunter.plat.core.service.local.imp;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantVO;
import com.kylinhunter.plat.core.dao.mapper.TenantMapper;
import com.kylinhunter.plat.core.service.local.TenantService;
import com.kylinhunter.plat.core.service.local.interceptor.TenantDeleteInterceptor;
import com.kylinhunter.plat.core.service.local.interceptor.TenantSaveOrUpdateInterceptor;
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

    public TenantServiceImp(TenantSaveOrUpdateInterceptor tenantSaveOrUpdateInterceptor,
                            TenantDeleteInterceptor tenantDeleteInterceptor) {
        this.saveOrUpdateInterceptor = tenantSaveOrUpdateInterceptor;
        this.deleteInterceptor = tenantDeleteInterceptor;
    }

    @PostConstruct
    @Override
    public void init() {
        super.init();
        this.tenantSupported = false;
    }
    @Override
    public Tenant queryByCode(String code) {
        LambdaQueryWrapper<Tenant> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Tenant::getSysDeleteFlag, false);
        queryWrapper.eq(Tenant::getCode, code);
        return this.baseMapper.selectOne(queryWrapper);
    }
}