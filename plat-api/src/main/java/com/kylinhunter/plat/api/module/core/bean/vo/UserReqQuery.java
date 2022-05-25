package com.kylinhunter.plat.api.module.core.bean.vo;

import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * UserReqQuery 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-01-01
 */
@Data
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@Accessors(chain = true)
@ApiModel(value = "UserReqQuery", description = "")
@NoArgsConstructor
public class UserReqQuery extends ReqQueryPage {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "根据roleId过滤")
    private String roleId;

}