package com.kylinhunter.plat.dao.service.local.interceptor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.entity.constants.SysCols;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.query.ReqById;
import com.kylinhunter.plat.api.bean.vo.query.ReqByIds;
import com.kylinhunter.plat.api.bean.vo.query.ReqPage;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;

import io.github.kylinhunter.commons.bean.BeanCopyUtils;
import io.github.kylinhunter.commons.exception.embed.biz.DBException;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 22:59
 **/
@Component
@Primary
public class QueryAccurateInterceptor<T extends BaseEntity, C extends ReqCreate, U extends ReqUpdate,
        Z extends Resp, V extends VO, Q extends ReqPage> extends BasicInterceptor<T, C, U, Z, V, Q> {

    public QueryWrapper<T> before(ReqById reqById, boolean tenantSupported) {
        QueryWrapper<T> query = Wrappers.query();
        if (tenantSupported) {
            final String tenantId = this.checkAndGetTenantId();
            query.eq(SysCols.SYS_TENANT_ID, tenantId);
        }
        if (!reqById.isWithLogicDelData()) {
            query.eq(SysCols.SYS_DELETE_FLAG, "0");
        }
        return query;

    }

    public Z after(ReqById reqById, T enity, Z responseBean) {
        if (enity != null) {
            BeanCopyUtils.copyProperties(enity, responseBean);
            return responseBean;
        }
        return null;

    }

    public QueryWrapper<T> before(ReqByIds reqByIds, boolean tenantSupported) {
        QueryWrapper<T> query = Wrappers.query();
        if (tenantSupported) {
            final String tenantId = this.checkAndGetTenantId();
            query.eq(SysCols.SYS_TENANT_ID, tenantId);
        }
        if (!reqByIds.isWithLogicDelData()) {
            query.eq(SysCols.SYS_DELETE_FLAG, "0");
        }
        return query;
    }

    public List<Z> after(ReqByIds reqByIds, List<T> entities, Class<Z> respClass) {
        if (entities != null) {
            return entities.stream().map(bean -> {

                try {
                    Z response = respClass.newInstance();
                    BeanCopyUtils.copyProperties(bean, response);
                    return response;
                } catch (Exception e) {
                    throw new DBException("copy bean error", e);
                }
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();

    }

}
