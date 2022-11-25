package io.github.kylinhunter.plat.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.github.kylinhunter.plat.core.init.SystemDataInitializer;
import io.github.kylinhunter.plat.web.response.DefaultResponse;
import io.github.kylinhunter.plat.web.response.Response;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-03 23:19
 **/
@RestController
@RequestMapping("/api/v1/initialize")
@Api(value = "Tenant相关接口")
@RequiredArgsConstructor
public class InitializeController {

    private final SystemDataInitializer systemDataInitializer;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Response<Boolean> initialize() {
        return new DefaultResponse<>(systemDataInitializer.init(true));
    }
}
