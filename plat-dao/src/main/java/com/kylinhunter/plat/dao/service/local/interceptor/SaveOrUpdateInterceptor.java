package com.kylinhunter.plat.dao.service.local.interceptor;

import java.time.LocalDateTime;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import com.kylinhunter.plat.commons.bean.BeanCopyUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-08 23:40
 **/
@Component
@Primary
public class SaveOrUpdateInterceptor<T extends BaseEntity, C extends ReqCreate, U extends ReqUpdate,
        Z extends Resp, V extends VO, Q extends ReqQueryPage> extends Interceptor<T, C, U, Z, V, Q> {

    private final String[] skipProperties = new String[] {
            "id", "sysTenantId", "sysCreatedUserId", "sysCreatedUserName", "sysCreatedTime",
            "sysUpdateUserId", "sysUpdateUserName", "sysUpdateTime", "sysDeleteFlag", "sysOpLock"
    };

    protected void saveOrUpdateBefore(V vo) {

    }

    public Z saveOrUpdateAfter(V vo, Z z) {
        return z;
    }

    @SuppressWarnings("unchecked")
    public T before(C c, T entity) {
        saveOrUpdateBefore((V) c);
        BeanCopyUtils.copyProperties(c, entity, skipProperties);
        if (c.getUserContext() != null) {

            entity.setSysCreatedUserId(c.getUserContext().getCurrentUserId());
            entity.setSysCreatedUserName(c.getUserContext().getCurrentUserName());
            entity.setSysUpdateUserId(c.getUserContext().getCurrentUserId());
            entity.setSysUpdateUserName(c.getUserContext().getCurrentUserName());
        }
        entity.setSysDeleteFlag(false);
        entity.setSysCreatedTime(LocalDateTime.now());
        entity.setSysUpdateTime(LocalDateTime.now());
        return entity;
    }

    @SuppressWarnings("unchecked")
    public Z after(C c, T enity, Z response) {

        BeanCopyUtils.copyProperties(enity, response);
        return saveOrUpdateAfter((V) c, response);
    }

    @SuppressWarnings("unchecked")
    public T before(U u, T entity) {
        saveOrUpdateBefore((V) u);
        BeanCopyUtils.copyProperties(u, entity, skipProperties);
        setUpdateMsg(u, entity);
        return entity;
    }

    @SuppressWarnings("unchecked")
    public Z after(U u, T entity, Z response) {

        BeanCopyUtils.copyProperties(entity, response);
        return saveOrUpdateAfter((V) u, response);

    }

}
