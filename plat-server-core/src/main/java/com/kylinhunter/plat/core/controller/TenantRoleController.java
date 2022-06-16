package com.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kylinhunter.plat.api.module.core.bean.entity.TenantRole;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleResp;
import com.kylinhunter.plat.api.module.core.bean.vo.TenantRoleVO;
import com.kylinhunter.plat.core.service.local.TenantRoleService;
import com.kylinhunter.plat.web.controller.CommonCurdController;

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