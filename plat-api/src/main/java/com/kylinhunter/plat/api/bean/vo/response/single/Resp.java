package com.kylinhunter.plat.api.bean.vo.response.single;

import com.kylinhunter.plat.api.bean.vo.constants.VoType;
import com.kylinhunter.plat.api.bean.vo.request.ReqTenant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 21:42
 **/
@Data
@ApiModel(value = "Resp", description = "Resp")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Resp extends ReqTenant {
    private static final long serialVersionUID = -8450405452557433712L;

    @ApiModelProperty(value = "id")
    @EqualsAndHashCode.Include
    protected String id;

    @ApiModelProperty(value = "voType", hidden = true)
    private VoType voType = VoType.RESP;

}
