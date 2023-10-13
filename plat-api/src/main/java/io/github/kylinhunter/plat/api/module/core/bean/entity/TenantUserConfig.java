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
package io.github.kylinhunter.plat.api.module.core.bean.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author biji'an
 * @since 2022-06-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("kplat_tenant_user_config")
@ApiModel(value = "TenantUserConfig")
public class TenantUserConfig extends BaseEntity {

  @ApiModelProperty(value = "type: 0 string")
  private Integer type;

  @ApiModelProperty(value = "status")
  private Integer status;

  @ApiModelProperty(value = "description")
  private String description;

  @ApiModelProperty(value = "userid")
  private String userId;

  @ApiModelProperty(value = "code")
  private String code;

  @ApiModelProperty(value = "value")
  private String value;
}
