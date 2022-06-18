package com.kylinhunter.plat.dao.service.local.interceptor;

import java.time.LocalDateTime;
import java.util.List;

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

    protected void checkTenantData(String tenantId, T entity) {
        if (!tenantId.equals(entity.getSysTenantId())) {
            throw new DBException("check tenantId invalid：" + tenantId + "/" + entity.getSysTenantId());

        }
    }

    protected void checkTenantData(String tenantId, List<T> entities) {

        entities.forEach(entity -> {
            if (!tenantId.equals(entity.getSysTenantId())) {
                throw new DBException("check tenantId invalid：" + tenantId + "/" + entity.getSysTenantId());
            }
        });
    }

    protected String checkAndGetTenantId() {

        String tenantId = userContextHandler.get(true).getTenantId();
        if (StringUtils.isEmpty(tenantId)) {
            throw new DBException("tenantId is emtpy");
        }
        return tenantId;

    }

}