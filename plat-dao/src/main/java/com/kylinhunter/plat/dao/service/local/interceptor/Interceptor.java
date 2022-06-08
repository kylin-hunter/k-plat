package com.kylinhunter.plat.dao.service.local.interceptor;

import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.api.bean.vo.request.Req;
import com.kylinhunter.plat.api.bean.vo.response.single.Resp;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 22:59
 **/
@Component
public class Interceptor<T extends BaseEntity, C extends ReqCreate, U extends ReqUpdate,
        Z extends Resp, V extends VO, Q extends ReqQueryPage> {

    protected void setUpdateMsg(Req u, T entity) {
        if (u.getUserContext() != null && !StringUtils
                .isEmpty(u.getUserContext().getCurrentUserId())) { // 防止
            entity.setSysUpdateUserId(u.getUserContext().getCurrentUserId());
            entity.setSysUpdateUserName(u.getUserContext().getCurrentUserName());
        }
        entity.setSysUpdateTime(LocalDateTime.now());
    }
}
