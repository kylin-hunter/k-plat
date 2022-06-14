package com.kylinhunter.plat.core.init;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.entity.User;
import com.kylinhunter.plat.api.module.core.bean.vo.UserReqCreate;

import lombok.Getter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 02:03
 **/
@Component
@Getter
public class UserInitDatas extends BasicInitData<UserReqCreate, User> {
    public static final String USER_ADMIN_CODE = "admin";
    public static final String USER_TEST_CODE = "test";

    private UserReqCreate userAdmin = createUserAdmin();
    private UserReqCreate userTest = createUserTest();

    private UserReqCreate createUserAdmin() {

        UserReqCreate userReqCreate = new UserReqCreate();
        userReqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
        userReqCreate.setUserCode(USER_ADMIN_CODE);
        userReqCreate.setUserName(USER_ADMIN_CODE);
        userReqCreate.setPassword(USER_ADMIN_CODE);
        userReqCreate.setSource("0");
        userReqCreate.setType(1);
        userReqCreate.setStatus(0);
        userReqCreate.setDescription(USER_ADMIN_CODE);
        this.addCreateData(userReqCreate.getUserCode(), userReqCreate);
        return userReqCreate;
    }

    private UserReqCreate createUserTest() {

        UserReqCreate userReqCreate = new UserReqCreate();
        userReqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
        userReqCreate.setUserCode(USER_TEST_CODE);
        userReqCreate.setUserName(USER_TEST_CODE);
        userReqCreate.setPassword(USER_TEST_CODE);
        userReqCreate.setSource("0");
        userReqCreate.setType(0);
        userReqCreate.setStatus(0);
        userReqCreate.setDescription(USER_TEST_CODE);
        this.addCreateData(userReqCreate.getUserCode(), userReqCreate);
        return userReqCreate;
    }

}
