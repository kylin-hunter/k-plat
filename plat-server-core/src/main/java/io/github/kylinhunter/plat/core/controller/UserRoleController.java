package io.github.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.kylinhunter.plat.api.module.core.bean.entity.UserRole;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserRoleVO;
import io.github.kylinhunter.plat.core.service.local.UserRoleService;
import io.github.kylinhunter.plat.web.controller.CommonCurdController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * UserRoleController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
@RestController
@RequestMapping("/api/v1/core/user_roles")
@Api(value = "UserRole相关接口")
@RequiredArgsConstructor
public class UserRoleController extends
        CommonCurdController<UserRoleService,UserRoleReqCreate,
            UserRoleReqUpdate, UserRoleResp, UserRoleVO, UserRoleReqQuery,UserRole> {

}