package io.github.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantUser;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantUserVO;
import io.github.kylinhunter.plat.core.service.local.TenantUserService;
import io.github.kylinhunter.plat.web.controller.CommonCurdController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * TenantUserController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-15
 */
@RestController
@RequestMapping("/api/v1/core/tenant_user")
@Api(value = "TenantUser相关接口")
@RequiredArgsConstructor
public class TenantUserController extends
        CommonCurdController<TenantUserService,TenantUserReqCreate,
            TenantUserReqUpdate, TenantUserResp, TenantUserVO, TenantUserReqQuery,TenantUser> {

}