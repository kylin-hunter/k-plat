package io.github.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantRole;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRoleVO;
import io.github.kylinhunter.plat.core.service.local.TenantRoleService;
import io.github.kylinhunter.plat.web.controller.CommonCurdController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * TenantRoleController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-17
 */
@RestController
@RequestMapping("/api/v1/core/tenant_roles")
@Api(value = "TenantRole相关接口")
@RequiredArgsConstructor
public class TenantRoleController extends
        CommonCurdController<TenantRoleService,TenantRoleReqCreate,
            TenantRoleReqUpdate, TenantRoleResp, TenantRoleVO, TenantRoleReqQuery,TenantRole> {

}