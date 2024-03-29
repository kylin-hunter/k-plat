package io.github.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.kylinhunter.plat.api.module.core.bean.entity.Role;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.RoleVO;
import io.github.kylinhunter.plat.core.service.local.RoleService;
import io.github.kylinhunter.plat.web.controller.CommonCurdController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * RoleController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-11
 */
@RestController
@RequestMapping("/api/v1/core/roles")
@Api(value = "Role相关接口")
@RequiredArgsConstructor
public class RoleController extends
        CommonCurdController<RoleService,RoleReqCreate,
            RoleReqUpdate, RoleResp, RoleVO, RoleReqQuery,Role> {

}