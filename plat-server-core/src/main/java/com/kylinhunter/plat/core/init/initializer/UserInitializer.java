package com.kylinhunter.plat.core.init.initializer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.auth.context.UserContextHandler;
import com.kylinhunter.plat.api.context.UserContext;
import com.kylinhunter.plat.api.module.core.bean.entity.User;
import com.kylinhunter.plat.api.module.core.bean.vo.UserReqCreate;
import com.kylinhunter.plat.core.init.data.UserInitData;
import com.kylinhunter.plat.core.service.local.UserService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-12 23:53
 **/
@Component
@RequiredArgsConstructor
@Slf4j
@Setter
@Getter
public class UserInitializer extends BasicInitializer {
    private final UserInitData userInitData;
    private final UserService userService;
    private final UserContextHandler userContextHandler;

    @Override
    public int order() {
        return 1;
    }

    @Override
    public void init() {
        UserReqCreate adminCreate = userInitData.getUserAdmin();
        initUserContext(adminCreate);

        userInitData.getInitDatas().values().forEach(userCreate -> {

            final String userCode = userCreate.getUserCode();
            final User user = userService.queryByUserCode(userCode);
            if (user != null) {
                log.info("default userCreate {} exist", userCode);
                userInitData.addDbData(userCode, user);
            } else {
                userService.save(userCreate);
                log.info("default userCreate {} created", userCode);
                userInitData.addDbData(userCode, userService.queryByUserCode(userCode));
            }
        });

    }

    private UserContext initUserContext(UserReqCreate userReqCreate) {
        User user = new User();
        BeanUtils.copyProperties(userReqCreate, user);
        UserContext userContext = userContextHandler.create(user);
        log.info("init userContext {}", userContext);
        return userContext;
    }

}
