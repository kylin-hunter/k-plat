package com.kylinhunter.plat.core.service.local.interceptor;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantVO;
import com.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 17:05
 **/
@Component
public class TenantSaveOrUpdateInterceptor extends
        SaveOrUpdateInterceptor<Tenant, TenantReqCreate, TenantReqUpdate, TenantResp, TenantVO, TenantReqQuery> {

    @Override
    public void saveOrUpdateBefore(TenantVO vo) {
    }

}
