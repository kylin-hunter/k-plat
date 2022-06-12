package com.kylinhunter.plat.core.service.local.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.entity.User;
import com.kylinhunter.plat.api.module.core.bean.vo.UserReqCreate;
import com.kylinhunter.plat.api.module.core.bean.vo.UserReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.UserReqUpdate;
import com.kylinhunter.plat.api.module.core.bean.vo.UserResp;
import com.kylinhunter.plat.api.module.core.bean.vo.UserVO;
import com.kylinhunter.plat.commons.codec.PasswordUtil;
import com.kylinhunter.plat.commons.exception.inner.ParamException;
import com.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 17:05
 **/
@Component
public class UserSaveOrUpdateInterceptor extends
        SaveOrUpdateInterceptor<User, UserReqCreate, UserReqUpdate, UserResp, UserVO, UserReqQuery> {

    @Override
    public void saveOrUpdateBefore(UserVO vo) {
        String password = vo.getPassword();
        if (!StringUtils.isEmpty(password)) {
            vo.setPassword(PasswordUtil.encode(password));
        } else {
            throw new ParamException("invalid password");
        }
    }

}