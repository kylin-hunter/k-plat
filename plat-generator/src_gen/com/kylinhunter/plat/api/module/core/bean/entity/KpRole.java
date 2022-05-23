package com.kylinhunter.plat.api.module.core.bean.entity;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.Version;
import java.time.LocalDateTime;
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
 * @since 2022-05-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value="KpRole对象", description="")
public class KpRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "租户ID")
    private String sysTenantId;

    @ApiModelProperty(value = "系统更新时间")
    private LocalDateTime sysAutoUpdated;

    @ApiModelProperty(value = "创建人userid")
    private String sysCreatedUserId;

    @ApiModelProperty(value = "创建人username")
    private String sysCreatedUserName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime sysCreatedTime;

    @ApiModelProperty(value = "最后编辑人userid")
    private String sysUpdateUserId;

    @ApiModelProperty(value = "最后编辑人username")
    private String sysUpdateUserName;

    @ApiModelProperty(value = "最后编辑时间")
    private LocalDateTime sysUpdateTime;

    @ApiModelProperty(value = "0 未删除 1删除")
    private Boolean sysDeleteFlag;

    @ApiModelProperty(value = "乐观锁")
    private Integer sysOpLock;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    @ApiModelProperty(value = "角色名")
    private String roleName;

    @ApiModelProperty(value = "角色类型")
    private Integer type;

    @ApiModelProperty(value = "角色状态，预留")
    private Integer status;

    @ApiModelProperty(value = "描述")
    private String description;


}
