package com.kylinhunter.plat.api.bean.vo.query;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.kylinhunter.plat.commons.exception.inner.ParamException;

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
@ApiModel(value = "ReqByIds 对象", description = "ReqByIds")
@NoArgsConstructor
@AllArgsConstructor
public class ReqByIds extends ReqQuery implements Serializable {
    private static final long serialVersionUID = -8450405452557433712L;
    @ApiModelProperty(value = "批量查询主键", required = true)
    @NotEmpty
    private Collection<String> ids;

    public static ReqByIds of(String idArr) {
        List<String> allIds = Lists.newArrayList();
        String[] ids = StringUtils.split(idArr, ',');
        for (String id : ids) {
            if (!StringUtils.isEmpty(id)) {
                allIds.add(id);
            }
        }
        if (allIds.size() > 100) {
            throw new ParamException(" limit: " + 100);
        }
        return new ReqByIds(allIds);
    }

}
