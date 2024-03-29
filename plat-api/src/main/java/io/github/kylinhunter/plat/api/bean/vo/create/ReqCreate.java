package io.github.kylinhunter.plat.api.bean.vo.create;

import io.github.kylinhunter.plat.api.bean.vo.constants.ReqType;
import io.github.kylinhunter.plat.api.bean.vo.constants.VoType;
import io.github.kylinhunter.plat.api.bean.vo.request.Req;

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
@ApiModel(value = "ReqCreate", description = "ReqCreate")
public class ReqCreate extends Req {
    @ApiModelProperty(value = "primary key", hidden = true)
    @EqualsAndHashCode.Include
    private String id;

    public ReqCreate() {
        super(VoType.CREATE, ReqType.CREATE);
    }

    public ReqCreate(String id) {
        this();
        this.id = id;
    }
}