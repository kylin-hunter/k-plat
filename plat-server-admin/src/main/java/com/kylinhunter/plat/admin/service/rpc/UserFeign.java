package com.kylinhunter.plat.admin.service.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kylinhunter.plat.api.module.core.vo.UserReqQuery;
import com.kylinhunter.plat.api.module.core.vo.UserSysResp;
import com.kylinhunter.plat.api.page.PageData;
import com.kylinhunter.plat.web.response.DefaultResponse;

@FeignClient(value = "plat-server-core")
public interface UserFeign {

    @RequestMapping(value = "/api/v1/core/user", method = RequestMethod.GET)
    DefaultResponse<PageData<UserSysResp>> query(@RequestParam UserReqQuery reqQueryPage);
}


