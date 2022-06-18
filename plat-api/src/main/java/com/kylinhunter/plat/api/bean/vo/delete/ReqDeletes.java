package com.kylinhunter.plat.api.bean.vo.delete;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.kylinhunter.plat.api.bean.vo.constants.ReqType;
import com.kylinhunter.plat.api.bean.vo.constants.VoType;
import com.kylinhunter.plat.api.bean.vo.request.Req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 21:42
 **/
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReqDelete", description = "ReqDelete")
public class ReqDeletes extends Req implements Serializable {
    private static final long serialVersionUID = -8450405452557433712L;

    @ApiModelProperty(value = "physical", hidden = true, required = true)
    private boolean physical = true;

    @ApiModelProperty(value = "ids", required = true)
    @NotEmpty
    private Collection<String> ids;

    public ReqDeletes() {
        super(VoType.DELETE, ReqType.DELETE);
    }

    public ReqDeletes(Collection<String> ids) {
        this();
        this.ids = ids;
    }

    public static ReqDeletes of(String idArr) {
        List<String> allIds = Lists.newArrayList();
        String[] ids = StringUtils.split(idArr, ',');
        for (String id : ids) {
            if (!StringUtils.isEmpty(id)) {
                allIds.add(id);
            }
        }
        return new ReqDeletes(allIds);
    }

    public boolean isBatch() {

        return ids != null && ids.size() > 0;

    }

}
