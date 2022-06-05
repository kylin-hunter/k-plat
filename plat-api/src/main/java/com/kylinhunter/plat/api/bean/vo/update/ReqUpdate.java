package com.kylinhunter.plat.api.bean.vo.update;

import javax.validation.constraints.NotBlank;

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
 * @date 2022/5/12
 **/
@Getter
@Setter
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@ApiModel(value = "ReqUpdate", description = "ReqUpdate")
public class ReqUpdate extends Req {
    @ApiModelProperty(value = "primary key")
    @NotBlank
    @EqualsAndHashCode.Include
    private String id;

    public ReqUpdate() {
        super(VoType.UPDATE);
    }

    public ReqUpdate(String id) {
        super(VoType.UPDATE);
        this.id = id;
    }
}