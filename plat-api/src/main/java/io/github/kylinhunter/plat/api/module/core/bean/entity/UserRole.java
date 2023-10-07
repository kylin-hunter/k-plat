package io.github.kylinhunter.plat.api.module.core.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author biji'an
 * @since 2023-10-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("kplat_user_role")
@ApiModel(value="UserRole对象", description="")
public class UserRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "角色 id")
    private String roleId;


}
