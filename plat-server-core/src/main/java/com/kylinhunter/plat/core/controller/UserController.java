package com.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kylinhunter.plat.api.module.core.enity.User;
import com.kylinhunter.plat.api.module.core.service.local.UserService;
import com.kylinhunter.plat.api.module.core.vo.UserReqCreate;
import com.kylinhunter.plat.api.module.core.vo.UserReqQuery;
import com.kylinhunter.plat.api.module.core.vo.UserReqUpdate;
import com.kylinhunter.plat.api.module.core.vo.UserSysResp;
import com.kylinhunter.plat.web.controller.CommonCurdController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date  2022-01-06 22:29
 **/
@RequestMapping("/api/v1/core/user")
@Api(value = "User相关接口")
@RequiredArgsConstructor
@RestController
public class UserController extends CommonCurdController<UserService, UserReqCreate, UserReqUpdate, UserSysResp,
        UserReqQuery, User> {
}
