package com.kylinhunter.plat.admin.service.rpc;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import com.kylinhunter.plat.api.module.core.bean.vo.UserReqQuery;
import com.kylinhunter.plat.web.response.DefaultResponse;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-04 02:45
 **/

@Component
public class EchoServiceFallback implements UserFeign {
    @Override
    public DefaultResponse userQuery(@RequestParam UserReqQuery reqQueryPage) {
        return new DefaultResponse<>(-1, "请求过于频繁");
    }
}