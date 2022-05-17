package com.kylinhunter.plat.api.bean.vo.query;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import com.kylinhunter.plat.api.bean.vo.query.ReqQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-05 21:42
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = " ReqQueryById 对象", description = "公用的查询ID对象")
@NoArgsConstructor
public class ReqQueryById extends ReqQuery implements Serializable {
    private static final long serialVersionUID = -8450405452557433712L;

    @ApiModelProperty(value = "主键")
    @NotBlank
    private String id;

    public ReqQueryById(String id) {
        this.id = id;
    }

}
