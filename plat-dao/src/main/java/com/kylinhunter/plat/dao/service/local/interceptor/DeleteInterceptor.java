package com.kylinhunter.plat.dao.service.local.interceptor;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.bean.vo.delete.ReqDeletes;
import com.kylinhunter.plat.api.bean.vo.query.ReqPage;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;

import io.github.kylinhunter.commons.exception.embed.ParamException;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-08 23:40
 **/
@Component
@Primary
public class DeleteInterceptor<T extends BaseEntity, C extends ReqCreate, U extends ReqUpdate,
        Z extends Resp, V extends VO, Q extends ReqPage> extends BasicInterceptor<T, C, U, Z, V, Q> {

    public void before(ReqDelete reqDelete, boolean tenantSupported, T entity) {
        if (StringUtils.isEmpty(reqDelete.getId())) {
            throw new ParamException("delete id is empty");
        }
        if (tenantSupported) {
            String tenantId = checkAndGetTenantId();
            checkTenantData(tenantId, entity);
        }
        if (!reqDelete.isPhysical()) {
            entity.setSysDeleteFlag(true);
            setUpdateMsg(reqDelete, entity);
        }

    }

    public boolean after(ReqDelete reqDelete, T entity) {
        return true;
    }

    public void before(ReqDeletes reqDeletes, boolean tenantSupported, List<T> entities) {
        if (CollectionUtils.isEmpty(reqDeletes.getIds())) {
            throw new ParamException("delete id is empty");
        }
        if (tenantSupported) {
            final String tenantId = checkAndGetTenantId();
            checkTenantData(tenantId, entities);
        }
        if (!reqDeletes.isPhysical()) {
            entities.forEach(entity -> {
                entity.setSysDeleteFlag(true);
                setUpdateMsg(reqDeletes, entity);
            });

        }

    }

    public boolean after(ReqDeletes reqDeletes, List<T> datas) {
        return true;
    }

}
