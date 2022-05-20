package com.kylinhunter.plat.api.bean.vo.request;

import com.kylinhunter.plat.api.bean.vo.constants.ReqType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 21:42
 **/
@Data
@ApiModel(value = "Req", description = "Req")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Req extends ReqTenant {
    private static final long serialVersionUID = -8450405452557433712L;

    @ApiModelProperty(value = "userContext", hidden = true)
    private UserContext userContext;

    @ApiModelProperty(value = "debug", hidden = true)
    private boolean debug = false;

    @ApiModelProperty(value = "reqType", hidden = true)
    private ReqType reqType;

    public Req(ReqType reqType) {
        this.reqType = reqType;
    }

    public void copyFrom(Req req) {
        this.userContext = req.userContext;
        this.reqType = req.reqType;
        this.debug = req.debug;
    }

}
