package com.kylinhunter.plat.dao.service.local.interceptor;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;

import com.kylinhunter.plat.api.auth.context.UserContextHandler;
import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
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
        Z extends Resp, V extends VO, Q extends ReqQueryPage> {

    @Autowired
    private UserContextHandler userContextHandler;

    protected void setCreateMsg(Req req, T entity) {
        UserContext userContext = req.getUserContext();
        if (userContext == null) {
            userContext = userContextHandler.get();
        }
        if (userContext == null || userContext.isDummy()) {
            throw new DBException(" no user content");
        }

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
        UserContext userContext = req.getUserContext();
        if (userContext == null) {
            userContext = userContextHandler.get();
        }
        if (userContext == null || userContext.isDummy()) {
            throw new DBException(" no user content");
        }

        entity.setSysUpdateUserId(userContext.getUserId());
        entity.setSysUpdateUserName(userContext.getUserName());
        entity.setSysUpdateTime(LocalDateTime.now());
    }
}
