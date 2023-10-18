package io.github.kylinhunter.plat.core.service.local.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kylinhunter.plat.api.module.core.bean.entity.SysUserConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigVO;
import io.github.kylinhunter.plat.api.module.core.constants.SysUserConfigCols;
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
public class SysUserConfigQueryInterceptor extends QueryInterceptor<
    SysUserConfig,
    SysUserConfigReqCreate,
    SysUserConfigReqUpdate,
    SysUserConfigResp,
    SysUserConfigVO,
    SysUserConfigReqQuery> {

  public SysUserConfigQueryInterceptor(
      SortComponent sortComponent,
      FilterComponent filterComponent) {
    super(sortComponent, filterComponent);
  }

  @Override
  public QueryWrapper<SysUserConfig> before(SysUserConfigReqQuery sysUserConfigReqQuery,
      boolean tenantSupported) {
    QueryWrapper<SysUserConfig> queryWrapper = super.before(sysUserConfigReqQuery, tenantSupported);
    queryWrapper.eq(SysUserConfigCols.USER_ID, TraceHolder.get().getUserContext().getUserId());
    return queryWrapper;
  }
}