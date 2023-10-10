package io.github.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUserRole;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserRoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserRoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserRoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserRoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserRoleVO;
import io.github.kylinhunter.plat.core.service.local.TenantUserRoleService;
import io.github.kylinhunter.plat.web.controller.CommonCurdController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * TenantUserRoleController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-10
 */
@RestController
@RequestMapping("/api/v1/core/tenant_user_roles")
@Api(value = "TenantUserRole相关接口")
@RequiredArgsConstructor
public class TenantUserRoleController extends
        CommonCurdController<TenantUserRoleService,TenantUserRoleReqCreate,
            TenantUserRoleReqUpdate, TenantUserRoleResp, TenantUserRoleVO, TenantUserRoleReqQuery,TenantUserRole> {

}