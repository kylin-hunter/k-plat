package com.kylinhunter.plat.dao.service.local.interceptor;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.api.bean.vo.request.Req;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import com.kylinhunter.plat.commons.exception.inner.biz.ex.DBException;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-08 23:40
 **/
@Component
@Primary
public class DeleteInterceptor<T extends BaseEntity, C extends ReqCreate, U extends ReqUpdate,
        Z extends Resp, V extends VO, Q extends ReqQueryPage> extends BasicInterceptor<T, C, U, Z, V, Q> {
    @ApiModelProperty(value = "withTenant", hidden = true)
    public void before(ReqDelete reqDelete, T entity) {

        tryCheckTenant(reqDelete, entity);
        if (!reqDelete.isPhysical()) {
            entity.setSysDeleteFlag(true);
            setUpdateMsg(reqDelete, entity);
        }

    }

    public boolean after(ReqDelete reqDelete, T entity) {
        return true;
    }

    private void tryCheckTenant(Req req, T entity) {
        if (req.isCheckTenant()) {
            String tenantId = req.getUserContext().getTenantId();
            if (StringUtils.isEmpty(tenantId)) {
                throw new DBException("tenantId is emtpy");

            } else {
                if (!tenantId.equals(entity.getSysTenantId())) {
                    throw new DBException("check tenantId   invalid");

                }
            }
        }
    }

}
