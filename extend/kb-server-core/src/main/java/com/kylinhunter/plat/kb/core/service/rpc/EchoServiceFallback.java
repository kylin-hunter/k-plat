package com.kylinhunter.plat.kb.core.service.rpc;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.kylinhunter.plat.api.module.core.bean.vo.UserReqQuery;
import io.github.kylinhunter.plat.web.response.DefaultResponse;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-04 02:45
 **/

@Component
public class EchoServiceFallback implements UserFeign {
    @Override
    public DefaultResponse userQuery(@RequestParam UserReqQuery reqQueryPage) {
        return new DefaultResponse<>(-1, "服务器异常，请稍后重试");
    }
}
