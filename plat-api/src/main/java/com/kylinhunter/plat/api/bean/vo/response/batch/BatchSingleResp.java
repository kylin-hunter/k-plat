
package com.kylinhunter.plat.api.bean.vo.response.batch;

import java.io.Serializable;

import com.kylinhunter.plat.api.bean.vo.request.ReqTenant;
import com.kylinhunter.plat.commons.exception.info.ErrInfos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author BiJi'an
 * @description
 * @date 2022/4/14
 **/
@Data
@Accessors(chain = true)
@ApiModel(value = "BatchSingleResp", description = "BatchSingleResp")
@AllArgsConstructor
public class BatchSingleResp<T extends ReqTenant> implements Serializable {

    @ApiModelProperty(value = "code")
    private int code = ErrInfos.CODE_SUCCESS;

    @ApiModelProperty(value = "msg")
    private String msg;

    @ApiModelProperty(value = "body")
    private T body;

    public BatchSingleResp(T body) {
        this.body = body;
    }

}
