package com.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUserConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserConfigVO;
import com.kylinhunter.plat.core.service.local.TenantUserConfigService;
import com.kylinhunter.plat.web.controller.CommonCurdController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * TenantUserConfigController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-23
 */
@RestController
@RequestMapping("/api/v1/core/tenant_user_configs")
@Api(value = "TenantUserConfig相关接口")
@RequiredArgsConstructor
public class TenantUserConfigController extends
        CommonCurdController<TenantUserConfigService,TenantUserConfigReqCreate,
            TenantUserConfigReqUpdate, TenantUserConfigResp, TenantUserConfigVO, TenantUserConfigReqQuery,TenantUserConfig> {

}