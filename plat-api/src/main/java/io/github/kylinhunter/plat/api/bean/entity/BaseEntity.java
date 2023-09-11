/*
 * Copyright (C) 2023 The k-commons Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kylinhunter.plat.api.bean.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import io.github.kylinhunter.plat.api.constants.SwaggerConst;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:01
 */
@ApiModel(value = "BaseEntity", description = "BaseEntity")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BaseEntity implements java.io.Serializable {

  @ApiModelProperty(value = "primary key", hidden = true)
  @TableId(type = IdType.ASSIGN_UUID)
  @EqualsAndHashCode.Include
  protected String id;

  @ApiModelProperty(value = "sysTenantId", hidden = true)
  private String sysTenantId;

  @ApiModelProperty(value = "sysCreatedUserId", hidden = true)
  private String sysCreatedUserId;

  @ApiModelProperty(value = "sysCreatedUserName", hidden = true)
  private String sysCreatedUserName;

  @ApiModelProperty(
      value = "sysCreatedTime",
      hidden = true,
      example = SwaggerConst.SAMPLE_DATE_TIME)
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
