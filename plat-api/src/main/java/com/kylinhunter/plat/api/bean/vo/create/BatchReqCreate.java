package com.kylinhunter.plat.api.bean.vo.create;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022/5/11
 **/
@Data
@ApiModel(value = "BatchReqCreate", description = "BatchReqCreate")
public class BatchReqCreate<T extends ReqCreate> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "body")
    @NotNull
    private List<T> body;
}
