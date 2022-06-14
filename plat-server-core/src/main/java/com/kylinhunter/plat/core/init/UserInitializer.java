package com.kylinhunter.plat.core.init;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.auth.context.UserContextHandler;
import com.kylinhunter.plat.api.context.UserContext;
import com.kylinhunter.plat.api.module.core.bean.entity.User;
import com.kylinhunter.plat.api.module.core.bean.vo.UserReqCreate;
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
public class UserInitializer implements Initializer {
    private final UserInitDatas userInitDatas;
    private final UserService userService;
    private final UserContextHandler userContextHandler;

    @Override
    public void init() {
        UserReqCreate adminCreate = userInitDatas.getUserAdmin();
        initUserContext(adminCreate);

        userInitDatas.getCreateDatas().values().forEach(userCreate -> {

            final String userCode = userCreate.getUserCode();
            final User user = userService.queryByUserCode(userCode);
            if (user != null) {
                log.info("default userCreate {} exist", userCode);
                userInitDatas.addDbData(userCode, user);
            } else {
                userService.save(userCreate);
                log.info("default userCreate {} created", userCode);
                userInitDatas.addDbData(userCode, userService.queryByUserCode(userCode));
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
