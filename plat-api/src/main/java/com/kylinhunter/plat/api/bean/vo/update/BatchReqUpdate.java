package com.kylinhunter.plat.api.bean.vo.update;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022/5/11
 **/
@Data
@ApiModel(value = "BatchReqUpdate", description = "BatchReqUpdate")
public class BatchReqUpdate<T extends ReqUpdate> implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "body")
    @NotNull
    private List<T> body;
}
