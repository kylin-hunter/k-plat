package com.kylinhunter.plat.api.bean.vo.delete;

import java.io.Serializable;
import java.util.Collection;

import javax.validation.constraints.NotEmpty;

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
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReqDelete", description = "ReqDelete")
public class ReqDelete extends Req implements Serializable {
    private static final long serialVersionUID = -8450405452557433712L;

    @ApiModelProperty(value = "physical", hidden = true, required = true)
    private boolean physical = true;

    @ApiModelProperty(value = "ids", required = true)
    @NotEmpty
    private Collection<String> ids;

    public ReqDelete() {
        super(VoType.DELETE, ReqType.DELETE);
    }

    public ReqDelete(Collection<String> ids) {
        this();
        this.ids = ids;
    }

}
