package com.kylinhunter.plat.dao.service.local.interceptor;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-08 23:40
 **/
@Component
@Primary
public class DeleteInterceptor<T extends BaseEntity, C extends ReqCreate, U extends ReqUpdate,
        Z extends Resp, V extends VO, Q extends ReqQueryPage> extends Interceptor<T, C, U, Z, V, Q> {

    public void before(ReqDelete reqDelete, T entity) {
        entity.setSysDeleteFlag(true);
        setUpdateMsg(reqDelete, entity);
    }

    public boolean after(ReqDelete reqDelete, T entity) {
        return true;
    }

}
