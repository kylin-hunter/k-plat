package io.github.kylinhunter.plat.core.service.local.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.kylinhunter.plat.api.bean.vo.query.ReqById;
import io.github.kylinhunter.plat.api.bean.vo.query.ReqByIds;
import io.github.kylinhunter.plat.api.module.core.bean.entity.SysUserConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigVO;
import io.github.kylinhunter.plat.api.module.core.constants.SysUserConfigCols;
import io.github.kylinhunter.plat.api.trace.TraceHolder;
import io.github.kylinhunter.plat.dao.service.local.interceptor.FindByIdInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-15 16:26
 */
@Component
public class SysUserConfigFindByIdInterceptor extends FindByIdInterceptor<
    SysUserConfig,
    SysUserConfigReqCreate,
    SysUserConfigReqUpdate,
    SysUserConfigResp,
    SysUserConfigVO,
    SysUserConfigReqQuery> {

  @Override
  public QueryWrapper<SysUserConfig> before(ReqById reqById, boolean tenantSupported) {
    QueryWrapper<SysUserConfig> queryWrapper = super.before(reqById, tenantSupported);
    queryWrapper.eq(SysUserConfigCols.USER_ID, TraceHolder.get().getUserContext().getUserId());
    return queryWrapper;

  }

  @Override
  public QueryWrapper<SysUserConfig> before(ReqByIds reqByIds, boolean tenantSupported) {

    QueryWrapper<SysUserConfig> queryWrapper = super.before(reqByIds, tenantSupported);
    queryWrapper.eq(SysUserConfigCols.USER_ID, TraceHolder.get().getUserContext().getUserId());
    return queryWrapper;
  }
}