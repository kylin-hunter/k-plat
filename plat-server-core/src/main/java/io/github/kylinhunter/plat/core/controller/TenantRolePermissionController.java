package io.github.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantRolePermission;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRolePermissionReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRolePermissionReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRolePermissionReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRolePermissionResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantRolePermissionVO;
import io.github.kylinhunter.plat.core.service.local.TenantRolePermissionService;
import io.github.kylinhunter.plat.web.controller.CommonCurdController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * TenantRolePermissionController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-10
 */
@RestController
@RequestMapping("/api/v1/core/tenant_role_permissions")
@Api(value = "TenantRolePermission相关接口")
@RequiredArgsConstructor
public class TenantRolePermissionController extends
        CommonCurdController<TenantRolePermissionService,TenantRolePermissionReqCreate,
            TenantRolePermissionReqUpdate, TenantRolePermissionResp, TenantRolePermissionVO, TenantRolePermissionReqQuery,TenantRolePermission> {

}