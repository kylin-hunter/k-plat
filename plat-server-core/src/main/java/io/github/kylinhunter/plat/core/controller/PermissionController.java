package io.github.kylinhunter.plat.core.controller;

import io.github.kylinhunter.plat.api.module.core.bean.entity.Permission;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.PermissionVO;
import io.github.kylinhunter.plat.core.service.local.PermissionService;
import io.github.kylinhunter.plat.web.controller.CommonCurdController;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * PermissionController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
@RestController
@RequestMapping("/api/v1/core/permissions")
@Api(value = "Permission相关接口")
@RequiredArgsConstructor
public class PermissionController extends
        CommonCurdController<PermissionService,PermissionReqCreate,
            PermissionReqUpdate, PermissionResp, PermissionVO, PermissionReqQuery,Permission> {

}