package com.kylinhunter.plat.core.init.initializer;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.api.auth.context.UserContextHandler;
import io.github.kylinhunter.plat.api.context.UserContext;
import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import com.kylinhunter.plat.web.auth.PasswordUtil;
import com.kylinhunter.plat.core.init.data.UserInitDatas;
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
public class Order01UserInitializer extends BasicInitializer {
    private final UserInitDatas userInitData;
    private final UserService userService;
    private final UserContextHandler userContextHandler;

    @Override
    public void init() {

        userInitData.getInitDatas().values().forEach(userCreate -> {

            final String userCode = userCreate.getUserCode();
             User user = userService.queryByUserCode(userCode);
            if (user != null) {
                log.info("default userCreate {} exist", userCode);
                userInitData.addDbData(userCode, user);
            } else {
                user=new User();
                BeanUtils.copyProperties(userCreate,user);
                user.setPassword(PasswordUtil.encode(user.getPassword()));
                userService.save(user);
                log.info("default userCreate {} created", userCode);
                userInitData.addDbData(userCode, userService.queryByUserCode(userCode));
            }
        });
        User userAdmin = userInitData.getDbData(UserInitDatas.USER_ADMIN);
        initUserContext(userAdmin);
    }

    private UserContext initUserContext(User user) {
        UserContext userContext = userContextHandler.create(user);
        log.info("init userContext {}", userContext);
        return userContext;
    }

}
