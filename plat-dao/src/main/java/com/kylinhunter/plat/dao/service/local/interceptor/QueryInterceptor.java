package com.kylinhunter.plat.dao.service.local.interceptor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.entity.constants.SysCols;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryById;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryByIds;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import com.kylinhunter.plat.api.page.PageData;
import com.kylinhunter.plat.commons.bean.BeanCopyUtils;
import com.kylinhunter.plat.commons.util.ReflectionUtil;
import com.kylinhunter.plat.dao.service.local.component.FilterComponent;
import com.kylinhunter.plat.dao.service.local.component.SortComponent;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 22:59
 **/
@Component
@Primary
public class QueryInterceptor<T extends BaseEntity, C extends ReqCreate, U extends ReqUpdate,
        Z extends Resp, V extends VO, Q extends ReqQueryPage> extends BasicInterceptor<T, C, U, Z, V, Q> {

    @Autowired
    private SortComponent sortComponent;
    @Autowired
    private FilterComponent filterComponent;

    public void before(ReqQueryById reqQueryById) {
    }

    public Z after(ReqQueryById reqQueryById, T enity, Z responseBean) {
        if (enity != null) {
            BeanCopyUtils.copyProperties(enity, responseBean);
            return responseBean;
        }
        return null;

    }

    public void before(ReqQueryByIds reqQueryByIds) {
    }

    public List<Z> after(ReqQueryByIds reqQueryByIds, List<T> entities, Class<Z> respClass) {
        if (entities != null) {
            return entities.stream().map(bean -> {
                Z response = ReflectionUtil.newInstance(respClass);
                BeanCopyUtils.copyProperties(bean, response);
                return response;
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();

    }

    public QueryWrapper<T> query(Q q) {
        QueryWrapper<T> wrapper = Wrappers.query();
        if (q.isWithTenant()) {
            wrapper.eq(SysCols.SYS_TENANT_ID, q.getSysTenantId());
        }
        if (!q.isWithLogicDelData()) {
            wrapper.eq(SysCols.SYS_DELETE_FLAG, "0");
        }
        sortComponent.sort(wrapper, q);
        filterComponent.filter(wrapper, q);
        return wrapper;
    }

    public PageData<Z> after(Q reqQueryPage, Page<T> page, Class<Z> respClass) {
        PageData<Z> pageData = new PageData<>();
        pageData.setPn(page.getCurrent());
        pageData.setPs(page.getSize());
        pageData.setPages(page.getPages());
        pageData.setTotal(page.getTotal());
        for (T r : page.getRecords()) {
            Z response = ReflectionUtil.newInstance(respClass);
            BeanCopyUtils.copyProperties(r, response);
            pageData.getBody().add(response);
        }
        return pageData;

    }

}
