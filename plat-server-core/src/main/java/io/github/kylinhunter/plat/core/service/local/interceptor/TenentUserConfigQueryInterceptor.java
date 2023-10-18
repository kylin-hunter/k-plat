package io.github.kylinhunter.plat.core.service.local.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUserConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigVO;
import io.github.kylinhunter.plat.api.module.core.constants.TenantUserConfigCols;
import io.github.kylinhunter.plat.api.trace.TraceHolder;
import io.github.kylinhunter.plat.dao.service.local.component.FilterComponent;
import io.github.kylinhunter.plat.dao.service.local.component.SortComponent;
import io.github.kylinhunter.plat.dao.service.local.interceptor.QueryInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-15 16:58
 */
@Component
public class TenentUserConfigQueryInterceptor extends QueryInterceptor<
    TenantUserConfig,
    TenantUserConfigReqCreate,
    TenantUserConfigReqUpdate,
    TenantUserConfigResp,
    TenantUserConfigVO,
    TenantUserConfigReqQuery> {

  public TenentUserConfigQueryInterceptor(
      SortComponent sortComponent,
      FilterComponent filterComponent) {
    super(sortComponent, filterComponent);
  }

  @Override
  public QueryWrapper<TenantUserConfig> before(TenantUserConfigReqQuery TenantUserConfigReqQuery,
      boolean tenantSupported) {
    QueryWrapper<TenantUserConfig> queryWrapper = super.before(TenantUserConfigReqQuery, tenantSupported);
    queryWrapper.eq(TenantUserConfigCols.USER_ID, TraceHolder.get().getUserContext().getUserId());
    return queryWrapper;
  }
}