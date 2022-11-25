package com.kylinhunter.plat.core.service.local.interceptor;

import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserVO;
import com.kylinhunter.plat.core.init.data.UserInitDatas;
import io.github.kylinhunter.plat.dao.service.local.interceptor.DeleteInterceptor;

import io.github.kylinhunter.commons.exception.embed.ParamException;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 17:05
 **/
@Component
@RequiredArgsConstructor
public class UserDeleteInterceptor extends
        DeleteInterceptor<User, UserReqCreate, UserReqUpdate, UserResp, UserVO, UserReqQuery> {

    private final UserInitDatas userInitData;

    @Override
    public void before(ReqDelete reqDelete, boolean tenantSupported, User entity) {
        super.before(reqDelete, tenantSupported, entity);
        if (!userInitData.canBeDeleted(entity.getUserCode())) {
            throw new ParamException("can't delete ,for user code:" + entity.getUserCode());
        }
    }

}
