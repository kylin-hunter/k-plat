package io.github.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.kylinhunter.plat.api.module.core.bean.entity.TenantCatalog;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogTree;
import io.github.kylinhunter.plat.api.module.core.bean.vo.TenantCatalogVO;
import io.github.kylinhunter.plat.core.init.data.TenantCatalogInitDatas;
import io.github.kylinhunter.plat.core.service.local.TenantCatalogService;
import io.github.kylinhunter.plat.web.controller.CommonCurdController;
import io.github.kylinhunter.plat.web.response.DefaultResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * TenantCatalogController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-17
 */
@RestController
@RequestMapping("/api/v1/core/tenant_catalogs")
@Api(value = "TenantCatalog相关接口")
@RequiredArgsConstructor
public class TenantCatalogController extends
        CommonCurdController<TenantCatalogService, TenantCatalogReqCreate,
                TenantCatalogReqUpdate, TenantCatalogResp, TenantCatalogVO, TenantCatalogReqQuery, TenantCatalog> {

    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("tree获取全部数据")
    public DefaultResponse<TenantCatalogTree> tree() {

        return new DefaultResponse<>(this.service.tree(TenantCatalogInitDatas.DEFAULT_TYPE));
    }
}