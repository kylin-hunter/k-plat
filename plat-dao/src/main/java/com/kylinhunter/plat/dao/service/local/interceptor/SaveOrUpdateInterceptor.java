package com.kylinhunter.plat.dao.service.local.interceptor;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.query.ReqPage;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;

import io.github.kylinhunter.commons.bean.BeanCopyUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-08 23:40
 **/
@Component
@Primary
public class SaveOrUpdateInterceptor<T extends BaseEntity, C extends ReqCreate, U extends ReqUpdate,
        Z extends Resp, V extends VO, Q extends ReqPage> extends BasicInterceptor<T, C, U, Z, V, Q> {

    private final String[] createSkipProperties = new String[] {
            "sysTenantId", "sysCreatedUserId", "sysCreatedUserName", "sysCreatedTime",
            "sysUpdateUserId", "sysUpdateUserName", "sysUpdateTime", "sysDeleteFlag", "sysOpLock"
    };

    private final String[] updateSkipProperties = new String[] {
            "id", "sysTenantId", "sysCreatedUserId", "sysCreatedUserName", "sysCreatedTime",
            "sysUpdateUserId", "sysUpdateUserName", "sysUpdateTime", "sysDeleteFlag", "sysOpLock", "code"
    };

    protected void saveOrUpdateBefore(V vo) {

    }

    public Z saveOrUpdateAfter(V vo, Z z) {
        return z;
    }

    @SuppressWarnings("unchecked")
    public T before(C c, boolean tenantSupported, T entity) {
        if (tenantSupported) {
            this.checkAndGetTenantId();
        }
        saveOrUpdateBefore((V) c);
        BeanCopyUtils.copyProperties(c, entity, createSkipProperties);
        this.setCreateMsg(c, entity);
        return entity;
    }

    @SuppressWarnings("unchecked")
    public Z after(C c, T enity, Z response) {

        BeanCopyUtils.copyProperties(enity, response);
        return saveOrUpdateAfter((V) c, response);
    }

    @SuppressWarnings("unchecked")
    public T before(U u, boolean tenantSupported, T entity) {
        if (tenantSupported) {
            final String tenantId = this.checkAndGetTenantId();
            checkTenantData(tenantId, entity);
        }
        saveOrUpdateBefore((V) u);
        BeanCopyUtils.copyProperties(u, entity, updateSkipProperties);
        this.setUpdateMsg(u, entity);
        return entity;
    }

    @SuppressWarnings("unchecked")
    public Z after(U u, T entity, Z response) {

        BeanCopyUtils.copyProperties(entity, response);
        return saveOrUpdateAfter((V) u, response);

    }

}
