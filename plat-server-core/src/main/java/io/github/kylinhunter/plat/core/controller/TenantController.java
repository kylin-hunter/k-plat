package io.github.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Tenant;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantVO;
import io.github.kylinhunter.plat.core.service.local.TenantService;
import io.github.kylinhunter.plat.web.controller.CommonCurdController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * TenantController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-12
 */
@RestController
@RequestMapping("/api/v1/core/tenants")
@Api(value = "Tenant相关接口")
@RequiredArgsConstructor
public class TenantController extends
        CommonCurdController<TenantService,TenantReqCreate,
            TenantReqUpdate, TenantResp, TenantVO, TenantReqQuery,Tenant> {

}