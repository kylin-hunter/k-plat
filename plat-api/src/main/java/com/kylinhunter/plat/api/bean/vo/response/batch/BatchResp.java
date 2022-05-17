
package com.kylinhunter.plat.api.bean.vo.response.batch;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import com.kylinhunter.plat.api.bean.vo.request.ReqTenant;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangchaosan
 * @date 2022/1/5 11:35
 */
@Data
@ApiModel(value = "BatchResp", description = "BatchResp")
@NoArgsConstructor
public class BatchResp<T extends ReqTenant> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "详细信息")
    private List<BatchSingleResp<T>> body = Lists.newArrayList();


    public void addSingleResp(BatchSingleResp<T> batchSingleResp) {

        this.body.add(batchSingleResp);

    }
}
