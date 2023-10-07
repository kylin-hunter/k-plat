package io.github.kylinhunter.plat.core.controller;

import io.github.kylinhunter.plat.api.module.core.bean.entity.RolePermission;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RolePermissionVO;
import io.github.kylinhunter.plat.core.service.local.RolePermissionService;
import io.github.kylinhunter.plat.web.controller.CommonCurdController;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * RolePermissionController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
@RestController
@RequestMapping("/api/v1/core/role_permissions")
@Api(value = "RolePermission相关接口")
@RequiredArgsConstructor
public class RolePermissionController extends
        CommonCurdController<RolePermissionService,RolePermissionReqCreate,
            RolePermissionReqUpdate, RolePermissionResp, RolePermissionVO, RolePermissionReqQuery,RolePermission> {

}