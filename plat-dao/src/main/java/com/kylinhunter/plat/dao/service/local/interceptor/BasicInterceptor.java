package com.kylinhunter.plat.dao.service.local.interceptor;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.kylinhunter.plat.api.auth.context.UserContextHandler;
import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.query.ReqPage;
import com.kylinhunter.plat.api.bean.vo.request.Req;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import com.kylinhunter.plat.api.context.UserContext;
import com.kylinhunter.plat.commons.exception.inner.biz.ex.DBException;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 22:59
 **/
public class BasicInterceptor<T extends BaseEntity, C extends ReqCreate, U extends ReqUpdate,
        Z extends Resp, V extends VO, Q extends ReqPage> {

    @Autowired
    protected UserContextHandler userContextHandler;

    protected void setCreateMsg(Req req, T entity) {
        UserContext userContext = userContextHandler.get(true);

        entity.setSysTenantId(userContext.getTenantId());
        entity.setSysCreatedUserId(userContext.getUserId());
        entity.setSysCreatedUserName(userContext.getUserName());
        entity.setSysCreatedTime(LocalDateTime.now());

        entity.setSysUpdateUserId(userContext.getUserId());
        entity.setSysUpdateUserName(userContext.getUserName());
        entity.setSysUpdateTime(LocalDateTime.now());

        entity.setSysDeleteFlag(false);
    }

    protected void setUpdateMsg(Req req, T entity) {
        UserContext userContext = userContextHandler.get(true);

        entity.setSysUpdateUserId(userContext.getUserId());
        entity.setSysUpdateUserName(userContext.getUserName());
        entity.setSysUpdateTime(LocalDateTime.now());
    }

    protected void checkTenant(T entity) {

        String tenantId = userContextHandler.get(true).getTenantId();
        if (StringUtils.isEmpty(tenantId)) {
            throw new DBException("tenantId is emtpy");
        } else {
            if (!tenantId.equals(entity.getSysTenantId())) {
                throw new DBException("check tenantId   invalid");

            }
        }

    }

    protected void checkTenant() {

        String tenantId = userContextHandler.get(true).getTenantId();
        if (StringUtils.isEmpty(tenantId)) {
            throw new DBException("tenantId is emtpy");
        }

    }

}