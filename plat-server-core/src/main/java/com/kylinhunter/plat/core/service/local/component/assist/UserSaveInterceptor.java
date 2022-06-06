package com.kylinhunter.plat.core.service.local.component.assist;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.vo.UserVO;
import com.kylinhunter.plat.api.service.local.SaveInterceptor;
import com.kylinhunter.plat.commons.codec.PasswordUtil;
import com.kylinhunter.plat.commons.exception.inner.ParamException;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 17:05
 **/
@Component
public class UserSaveInterceptor extends SaveInterceptor<UserVO> {

    @Override
    public void saveBefore(UserVO vo) {
        String password = vo.getPassword();
        if (!StringUtils.isEmpty(password)) {
            vo.setPassword(PasswordUtil.encode(password));
        } else {
            throw new ParamException("invalid password");
        }
    }

}
