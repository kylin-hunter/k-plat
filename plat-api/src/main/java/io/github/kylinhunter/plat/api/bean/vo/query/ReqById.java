package io.github.kylinhunter.plat.api.bean.vo.query;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

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
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = " ReqById", description = "ReqById")
@NoArgsConstructor
public class ReqById extends ReqQuery implements Serializable {
    private static final long serialVersionUID = -8450405452557433712L;

    @ApiModelProperty(value = "primary key")
    @NotBlank
    private String id;

    public ReqById(String id) {
        this.id = id;
    }

    public static ReqById of(String id) {
        return new ReqById(id);
    }

}
