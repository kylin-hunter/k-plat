package com.kylinhunter.plat.core.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kylinhunter.plat.web.auth.JWTService;
import com.kylinhunter.plat.web.auth.Token;
import com.kylinhunter.plat.web.controller.CommonController;
import com.kylinhunter.plat.web.response.DefaultResponse;
import com.kylinhunter.plat.web.trace.TraceHandler;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * AuthRoleController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-01-01
 */
@RestController
@RequestMapping("/api/v1/auth/token")
@Api(value = "token相关")
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthController extends CommonController {

    private final JWTService jwtService;
    private final TraceHandler traceHandler;


    @PostMapping(value = "")
    @ApiOperation("创建token")
    public DefaultResponse<String> create(@Validated @RequestBody Token token) {
        return new DefaultResponse(jwtService.create(token));
    }

    @PostMapping(value = "/verify")
    @ApiOperation("校验token")
    public DefaultResponse<String> verify() {
        return new DefaultResponse(jwtService.verify(traceHandler.get().getToken()));
    }





}