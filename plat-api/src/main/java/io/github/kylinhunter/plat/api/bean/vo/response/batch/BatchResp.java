
package io.github.kylinhunter.plat.api.bean.vo.response.batch;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;
import io.github.kylinhunter.plat.api.bean.vo.BasicVO;

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
public class BatchResp<T extends BasicVO> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "body")
    private List<BatchSingleResp<T>> body = Lists.newArrayList();

    public void addSingleResp(T singleResp) {
        this.body.add(new BatchSingleResp<>(singleResp));
    }

    public void addSingleResp(int code, String msg, T singleResp) {
        this.body.add(new BatchSingleResp<>(code, msg, singleResp));
    }
}
