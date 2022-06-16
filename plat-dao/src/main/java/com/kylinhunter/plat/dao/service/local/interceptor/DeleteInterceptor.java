package com.kylinhunter.plat.dao.service.local.interceptor;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.bean.vo.query.ReqPage;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-08 23:40
 **/
@Component
@Primary
public class DeleteInterceptor<T extends BaseEntity, C extends ReqCreate, U extends ReqUpdate,
        Z extends Resp, V extends VO, Q extends ReqPage> extends BasicInterceptor<T, C, U, Z, V, Q> {
    @ApiModelProperty(value = "withTenant", hidden = true)
    public void before(ReqDelete reqDelete, boolean tenantSupported, T entity) {
        if (tenantSupported) {
            checkTenant(entity);
        }
        if (!reqDelete.isPhysical()) {
            entity.setSysDeleteFlag(true);
            setUpdateMsg(reqDelete, entity);
        }

    }

    public boolean after(ReqDelete reqDelete, T entity) {
        return true;
    }

}
