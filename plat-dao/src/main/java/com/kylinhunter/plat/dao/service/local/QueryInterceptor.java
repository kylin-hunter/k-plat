package com.kylinhunter.plat.dao.service.local;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.entity.constants.SysCols;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryById;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryByIds;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.page.PageData;
import com.kylinhunter.plat.commons.bean.BeanCopyUtils;
import com.kylinhunter.plat.commons.exception.inner.biz.ex.DBException;
import com.kylinhunter.plat.dao.service.local.component.FilterComponent;
import com.kylinhunter.plat.dao.service.local.component.SortComponent;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 22:59
 **/
@Component
public class QueryInterceptor<T extends BaseEntity, Q extends ReqQueryPage, Z extends Resp> {

    @Autowired
    private SortComponent sortComponent;
    @Autowired
    private FilterComponent filterComponent;

    protected Class<Z> respClass = currentRespClass();

    public void queryByIdsBefore(ReqQueryByIds reqQueryByIds) {
    }

    public List<Z> queryByIdsAfter(ReqQueryByIds reqQueryByIds, List<T> entities) {
        if (entities != null) {
            return entities.stream().map(bean -> {
                Z responseBean = createResponseBean();
                BeanCopyUtils.copyProperties(bean, responseBean);
                return responseBean;
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();

    }

    public void queryByIdBefore(ReqQueryById reqQueryById) {
    }

    public Z queryByIdAfter(ReqQueryById reqQueryById, T enity) {
        if (enity != null) {
            Z responseBean = createResponseBean();
            BeanCopyUtils.copyProperties(enity, responseBean);
            return responseBean;
        }
        return null;

    }

    public QueryWrapper<T> queryBefore(Q q) {
        QueryWrapper<T> wrapper = Wrappers.query();
        if (q.isWithTenant()) {
            wrapper.eq(SysCols.SYS_TENANT_ID, q.getTenantId());
        }
        if (!q.isWithLogicDelData()) {
            wrapper.eq(SysCols.SYS_DELETE_FLAG, "0");
        }
        sortComponent.sort(wrapper, q);
        filterComponent.writeToWrapper(wrapper, q);
        return wrapper;
    }

    public PageData<Z> queryAfter(Q reqQueryPage, Page<T> page) {
        PageData<Z> pageData = new PageData<>();
        pageData.setPn(page.getCurrent());
        pageData.setPs(page.getSize());
        pageData.setPages(page.getPages());
        pageData.setTotal(page.getTotal());
        for (T r : page.getRecords()) {
            Z docDraftRespVo = createResponseBean();
            BeanCopyUtils.copyProperties(r, docDraftRespVo);
            pageData.getBody().add(docDraftRespVo);
        }
        return pageData;

    }

    private Z createResponseBean() {
        try {
            return respClass.newInstance();
        } catch (Exception e) {
            throw new DBException("getEntityBean error", e);
        }
    }

    @SuppressWarnings("unchecked")
    private Class<Z> currentRespClass() {
        return (Class<Z>) ReflectionKit.getSuperClassGenericType(this.getClass(), ServiceImpl.class, 2);
    }
}
