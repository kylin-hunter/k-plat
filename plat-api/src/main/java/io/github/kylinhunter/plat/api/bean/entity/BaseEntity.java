package io.github.kylinhunter.plat.api.bean.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import io.github.kylinhunter.plat.api.constants.SwaggerConst;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:01
 **/
@ApiModel(value = "BaseEntity", description = "BaseEntity")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BaseEntity implements java.io.Serializable {

    @ApiModelProperty(value = "primary key", hidden = true)
    @TableId(type = IdType.ASSIGN_UUID)
    @EqualsAndHashCode.Include
    protected String id;
    /*
       @ApiModelProperty(value = "更新时间", hidden = true, example = SwaggerConst.SAMPLE_DATE_TIME)
       private LocalDateTime sysAutoUpdated;
    */
    @ApiModelProperty(value = "sysTenantId", hidden = true)
    private String sysTenantId;

    @ApiModelProperty(value = "sysCreatedUserId", hidden = true)
    private String sysCreatedUserId;

    @ApiModelProperty(value = "sysCreatedUserName", hidden = true)
    private String sysCreatedUserName;

    @ApiModelProperty(value = "sysCreatedTime", hidden = true, example = SwaggerConst.SAMPLE_DATE_TIME)
    private LocalDateTime sysCreatedTime;

    @ApiModelProperty(value = "sysUpdateUserId", hidden = true)
    private String sysUpdateUserId;

    @ApiModelProperty(value = "sysUpdateUserName", hidden = true)
    private String sysUpdateUserName;

    @ApiModelProperty(value = "sysUpdateTime", hidden = true, example = SwaggerConst.SAMPLE_DATE_TIME)
    private LocalDateTime sysUpdateTime;

    @ApiModelProperty(value = "sysDeleteFlag", hidden = true)
    private Boolean sysDeleteFlag;

    @ApiModelProperty(value = "sysOpLock", hidden = true)
    @Version
    private Integer sysOpLock;

}
