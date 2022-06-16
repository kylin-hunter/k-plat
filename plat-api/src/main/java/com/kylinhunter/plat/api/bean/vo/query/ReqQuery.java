package com.kylinhunter.plat.api.bean.vo.query;

import java.io.Serializable;

import com.kylinhunter.plat.api.bean.vo.constants.ReqType;
import com.kylinhunter.plat.api.bean.vo.constants.VoType;
import com.kylinhunter.plat.api.bean.vo.request.Req;

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
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ApiModel(value = "ReqQuery", description = "ReqQuery")
public class ReqQuery extends Req implements Serializable {
    private static final long serialVersionUID = -8450405452557433712L;

    @ApiModelProperty(value = "withLogicDelData", hidden = true)
    private boolean withLogicDelData = false;

    public ReqQuery() {
        super(VoType.QUERY, ReqType.QUERY);
    }
}
