package io.github.kylinhunter.plat.web.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import io.github.kylinhunter.plat.api.bean.vo.VO;
import io.github.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDeletes;
import io.github.kylinhunter.plat.api.bean.vo.query.ReqById;
import io.github.kylinhunter.plat.api.bean.vo.query.ReqByIds;
import io.github.kylinhunter.plat.api.bean.vo.query.ReqPage;
import io.github.kylinhunter.plat.api.bean.vo.response.single.DefaultSysResp;
import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.github.kylinhunter.plat.api.page.PageData;
import io.github.kylinhunter.plat.api.service.local.CommonService;
import io.github.kylinhunter.plat.web.response.DefaultResponse;

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
        Q extends ReqPage, T extends BaseEntity> {

    @Autowired
    protected S service;

    @PostConstruct
    private void init() {
        log.info("init controller {} ok", this.getClass().getName());
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("新建")

    public DefaultResponse<Z> create(@RequestBody @Validated X reqCreate) {

        return new DefaultResponse<>(service.save(reqCreate));
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation("修改")
    public DefaultResponse<Z> update(@RequestBody @Validated Y reqUpdate) {
        return new DefaultResponse<>(service.update(reqUpdate));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation("删除")
    public DefaultResponse<Boolean> delete(@Validated @NotBlank @PathVariable("id") String id) {
        return new DefaultResponse<>(service.delete(ReqDelete.of(id)));
    }

    @RequestMapping(value = "/batch", method = RequestMethod.DELETE)
    @ResponseBody
    @ApiOperation("删除(多个)")
    public DefaultResponse<Boolean> batchDelete(@Validated ReqDeletes reqDeletes) {
        return new DefaultResponse<>(service.delete(reqDeletes));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查看详情")
    public DefaultResponse<Z> get(@PathVariable("id") String id) {

        return new DefaultResponse<>(this.service.queryById(ReqById.of(id)));

    }

    @RequestMapping(value = "/batch", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("查看详情")
    public DefaultResponse<List<Z>> batchGet(@Validated ReqByIds reqByIds) {

        return new DefaultResponse<>(this.service.queryByIds(reqByIds));

    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation("分页获取全部数据")
    public DefaultResponse<PageData<Z>> list(@Validated Q reqQueryPage) {

        return new DefaultResponse<>(this.service.query(reqQueryPage));
    }

}
