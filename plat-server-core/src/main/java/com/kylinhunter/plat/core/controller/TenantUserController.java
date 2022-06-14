package com.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kylinhunter.plat.api.module.core.bean.entity.TenantUser;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantUserVO;
import com.kylinhunter.plat.core.service.local.TenantUserService;
import com.kylinhunter.plat.web.controller.CommonCurdController;

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