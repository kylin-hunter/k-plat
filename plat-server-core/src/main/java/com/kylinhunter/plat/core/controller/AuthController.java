package com.kylinhunter.plat.core.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kylinhunter.plat.core.service.local.AuthService;
import com.kylinhunter.plat.web.auth.LoginForm;
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
@Api(value = "auth相关")
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthController extends CommonController {

    private final TraceHandler traceHandler;
    private final AuthService authService;

    @PostMapping(value = "/login")
    @ApiOperation("login")
    public DefaultResponse<String> login(@Validated @RequestBody LoginForm loginForm) {
        return new DefaultResponse(authService.login(loginForm));
    }

    @PostMapping(value = "/auth/create_tenant_token")
    @ApiOperation("create_tenant_token")
    public DefaultResponse<String> createTenantToken(String tenantId) {

        return new DefaultResponse(authService.createTenantToken(tenantId, tenantId));
    }

    @PostMapping(value = "/auth/verify_token")
    @ApiOperation("verify_token")
    public DefaultResponse<String> verify() {
        return new DefaultResponse(authService.verify(traceHandler.get().getToken()));
    }

}