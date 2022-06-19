package com.kylinhunter.plat.kb.core.service.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kylinhunter.plat.api.module.core.bean.vo.UserReqQuery;
import com.kylinhunter.plat.api.module.core.bean.vo.UserResp;
import com.kylinhunter.plat.api.page.PageData;
import com.kylinhunter.plat.web.response.DefaultResponse;

@FeignClient(value = "plat-server-core", fallback = EchoServiceFallback.class)
public interface UserFeign {

    @RequestMapping(value = "/api/v1/core/users", method = RequestMethod.GET)
    DefaultResponse<PageData<UserResp>> userQuery( @SpringQueryMap UserReqQuery reqQueryPage);
}


class FeignConfiguration {

    public EchoServiceFallback echoServiceFallback() {
        return new EchoServiceFallback();
    }
}

