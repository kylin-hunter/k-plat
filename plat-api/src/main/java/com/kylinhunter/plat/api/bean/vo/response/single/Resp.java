package com.kylinhunter.plat.api.bean.vo.response.single;

import com.kylinhunter.plat.api.bean.vo.BasicVO;
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
@Getter
@Setter
@ApiModel(value = "Resp", description = "Resp")
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public class Resp extends BasicVO {
    private static final long serialVersionUID = -8450405452557433712L;

    @ApiModelProperty(value = "id")
    @EqualsAndHashCode.Include
    protected String id;

    public Resp() {
        super(VoType.RESP);
    }
}
