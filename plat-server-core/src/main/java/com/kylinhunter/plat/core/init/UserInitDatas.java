package com.kylinhunter.plat.core.init;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.api.module.core.bean.vo.UserReqCreate;

import lombok.Getter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 02:03
 **/
@Component
@Getter
public class UserInitDatas implements InitData {
    private static final String ADMIN_CODE = "admin";
    private static final String ADMIN_NAME = "admin";
    private UserReqCreate admin = createAdmin();

    private UserReqCreate createAdmin() {

        UserReqCreate userReqCreate = new UserReqCreate();
        userReqCreate.setId(UUID.randomUUID().toString().replace("-", ""));
        userReqCreate.setUserCode(ADMIN_CODE);
        userReqCreate.setUserName(ADMIN_NAME);
        userReqCreate.setPassword(ADMIN_CODE);
        userReqCreate.setSource("0");
        userReqCreate.setType(1);
        userReqCreate.setStatus(0);
        userReqCreate.setDescription(ADMIN_NAME);

        return userReqCreate;
    }

    @Override
    public boolean canBeModified(String code) {
        if (ADMIN_CODE.equalsIgnoreCase(code)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean canBeDeleted(String code) {
        if (ADMIN_CODE.equalsIgnoreCase(code)) {
            return false;
        }
        return true;
    }
}
