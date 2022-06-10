package com.kylinhunter.plat.web.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryById;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.api.bean.vo.response.single.DefaultSysResp;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import com.kylinhunter.plat.api.page.PageData;
import com.kylinhunter.plat.api.service.local.CommonService;
import com.kylinhunter.plat.web.response.DefaultResponse;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-06 22:52
 **/
@RequiredArgsConstructor
@Slf4j
public abstract class CommonCurdController<S extends CommonService<T, X, Y, Z, V, Q>, X extends ReqCreate,
        Y extends ReqUpdate,
        Z extends DefaultSysResp,
        V extends VO,
        Q extends ReqQueryPage, T extends BaseEntity> {

    @Autowired
    private S service;

    @PostConstruct
    private void init() {
        log.info("init controller {} ok", this.getClass().getName());
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("新建")

    public DefaultResponse<Z> create(@RequestBody @Validated X reqCreate) {

        return new DefaultResponse<>(service.save(reqCreate));
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("修改")
    public DefaultResponse<Z> update(@RequestBody @Validated Y reqUpdate) {
        return new DefaultResponse<>(service.update(reqUpdate));
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("删除")
    public DefaultResponse<Boolean> delete(@RequestBody @Validated ReqDelete commonreqDelete) {
        return new DefaultResponse<>(service.delete(commonreqDelete));
    }

    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查看详情")
    public DefaultResponse<Z> detail(@Validated ReqQueryById reqQueryById) {
        return new DefaultResponse<>(this.service.queryById(reqQueryById));

    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页获取全部数据")
    public DefaultResponse<PageData<Z>> list(@Validated Q reqQueryPage) {

        return new DefaultResponse<>(this.service.query(reqQueryPage));
    }

}
