package com.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantConfigVO;
import com.kylinhunter.plat.core.service.local.TenantConfigService;
import com.kylinhunter.plat.web.controller.CommonCurdController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * TenantConfigController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-23
 */
@RestController
@RequestMapping("/api/v1/core/tenant_configs")
@Api(value = "TenantConfig相关接口")
@RequiredArgsConstructor
public class TenantConfigController extends
        CommonCurdController<TenantConfigService,TenantConfigReqCreate,
            TenantConfigReqUpdate, TenantConfigResp, TenantConfigVO, TenantConfigReqQuery,TenantConfig> {

}