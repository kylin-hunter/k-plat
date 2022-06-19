package com.kylinhunter.plat.kb.core.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kylinhunter.plat.api.module.core.bean.vo.UserReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.UserResp;
import com.kylinhunter.plat.api.page.PageData;
import com.kylinhunter.plat.kb.core.service.rpc.UserFeign;
import com.kylinhunter.plat.web.response.DefaultResponse;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-06 22:29
 **/
@RequestMapping("/api/v1/core/users")
@Api(value = "User相关接口")
@RequiredArgsConstructor
@RestController
public class UserController {


   private final UserFeign client;

    @RequestMapping("")
    public DefaultResponse<PageData<UserResp>> hello(@Validated UserReqQuery reqQueryPage) {
        // FeignClient注解的接口自己不需要实现，可以直接的访问HelloServerApplication里定义的的地址
        return client.userQuery(reqQueryPage);
    }

}
