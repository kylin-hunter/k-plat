package io.github.kylinhunter.plat.core.service.local.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqCreate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqQuery;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqUpdate;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserResp;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserVO;
import io.github.kylinhunter.plat.web.auth.PasswordUtil;
import io.github.kylinhunter.plat.core.init.data.UserInitDatas;
import io.github.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;

import io.github.kylinhunter.commons.exception.embed.ParamException;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 17:05
 **/
@Component
@RequiredArgsConstructor
public class UserSaveOrUpdateInterceptor extends
        SaveOrUpdateInterceptor<User, UserReqCreate, UserReqUpdate, UserResp, UserVO, UserReqQuery> {

    private final UserInitDatas userInitData;

    @Override
    public void saveOrUpdateBefore(UserVO vo) {
        String password = vo.getPassword();
        if (!StringUtils.isEmpty(password)) {
            vo.setPassword(PasswordUtil.encode(password));
        } else {
            throw new ParamException("invalid password");
        }

        super.saveOrUpdateBefore(vo);

    }

    @Override
    public User before(UserReqUpdate reqUpdate, boolean tenantSupported, User entity) {
        if (!userInitData.canBeModified(entity.getUserCode())) {
            throw new ParamException("invalid user code:" + entity.getUserCode());
        }
        return super.before(reqUpdate, tenantSupported, entity);
    }
}
