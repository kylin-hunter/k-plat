package com.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kylinhunter.plat.api.module.core.bean.entity.SysUserConfig;
import com.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigResp;
import com.kylinhunter.plat.api.module.core.bean.vo.SysUserConfigVO;
import com.kylinhunter.plat.core.service.local.SysUserConfigService;
import com.kylinhunter.plat.web.controller.CommonCurdController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * SysUserConfigController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-23
 */
@RestController
@RequestMapping("/api/v1/core/sys_user_configs")
@Api(value = "SysUserConfig相关接口")
@RequiredArgsConstructor
public class SysUserConfigController extends
        CommonCurdController<SysUserConfigService,SysUserConfigReqCreate,
            SysUserConfigReqUpdate, SysUserConfigResp, SysUserConfigVO, SysUserConfigReqQuery,SysUserConfig> {

}