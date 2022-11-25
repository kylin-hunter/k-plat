package com.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.kylinhunter.plat.api.module.core.bean.entity.SysConfig;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysConfigReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysConfigReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysConfigReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysConfigResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.SysConfigVO;
import com.kylinhunter.plat.core.service.local.SysConfigService;
import com.kylinhunter.plat.web.controller.CommonCurdController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * SysConfigController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-23
 */
@RestController
@RequestMapping("/api/v1/core/sys_configs")
@Api(value = "SysConfig相关接口")
@RequiredArgsConstructor
public class SysConfigController extends
        CommonCurdController<SysConfigService,SysConfigReqCreate,
            SysConfigReqUpdate, SysConfigResp, SysConfigVO, SysConfigReqQuery,SysConfig> {

}