package com.kylinhunter.plat.api.bean.vo.query;

import java.io.Serializable;
import java.util.Collection;

import javax.validation.constraints.NotEmpty;

import com.kylinhunter.plat.api.bean.vo.query.ReqQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 21:42
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "ReqQueryByIds 对象", description = "ReqQueryByIds")
@NoArgsConstructor
@AllArgsConstructor
public class ReqQueryByIds extends ReqQuery implements Serializable {
    private static final long serialVersionUID = -8450405452557433712L;
    @ApiModelProperty(value = "批量查询主键", required = true)
    @NotEmpty
    private Collection<String> ids;

}
