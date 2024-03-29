package io.github.kylinhunter.plat.core.init.data;

import java.util.UUID;

import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.api.module.core.bean.entity.User;
import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqCreate;
import io.github.kylinhunter.plat.api.module.core.constants.UserStatus;
import io.github.kylinhunter.plat.api.module.core.constants.UserType;

import lombok.Getter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 02:03
 **/
@Component
@Getter
public class UserInitDatas extends BasicInitDatas<UserReqCreate, User> {
    public static final String USER_ADMIN = "admin";
    public static final String USER_TEST = "test";

    private UserReqCreate userAdmin = createUserAdmin();
    private UserReqCreate userTest = createUserTest();

    private UserReqCreate createUserAdmin() {

        UserReqCreate userReqCreate = new UserReqCreate();
        userReqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
        userReqCreate.setUserCode(USER_ADMIN);
        userReqCreate.setUserName(USER_ADMIN);
        userReqCreate.setPassword(USER_ADMIN);
        userReqCreate.setSource("0");
        userReqCreate.setType(UserType.SUPER_ADMIN.getCode());
        userReqCreate.setStatus(UserStatus.NORMAL.getCode());
        userReqCreate.setDescription(USER_ADMIN);
        this.addInitData(userReqCreate.getUserCode(), userReqCreate);
        return userReqCreate;
    }

    private UserReqCreate createUserTest() {

        UserReqCreate userReqCreate = new UserReqCreate();
        userReqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
        userReqCreate.setUserCode(USER_TEST);
        userReqCreate.setUserName(USER_TEST);
        userReqCreate.setPassword(USER_TEST);
        userReqCreate.setSource("0");
        userReqCreate.setType(UserType.USER.getCode());
        userReqCreate.setStatus(UserStatus.NORMAL.getCode());
        userReqCreate.setDescription(USER_TEST);
        this.addInitData(userReqCreate.getUserCode(), userReqCreate);
        return userReqCreate;
    }

}
