package com.kylinhunter.plat.api.bean.vo.request;

import com.kylinhunter.plat.api.bean.vo.BasicVO;
import com.kylinhunter.plat.api.bean.vo.VO;
import com.kylinhunter.plat.api.bean.vo.constants.ReqType;
import com.kylinhunter.plat.api.bean.vo.constants.VoType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 21:42
 **/
@Setter
@Getter
@ApiModel(value = "Req", description = "Req")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Req extends BasicVO implements VO {
    private static final long serialVersionUID = -8450405452557433712L;

    @ApiModelProperty(value = "userContext", hidden = true)
    private UserContext userContext;

    @ApiModelProperty(value = "debug", hidden = true)
    private boolean debug = false;

    @ApiModelProperty(value = "reqType", hidden = true)
    private ReqType reqType;

    public Req(VoType voType, ReqType reqType) {
        super(voType);
        this.reqType = reqType;
    }

    public void copyFrom(Req req) {
        this.userContext = req.userContext;
        this.debug = req.debug;
    }

}
