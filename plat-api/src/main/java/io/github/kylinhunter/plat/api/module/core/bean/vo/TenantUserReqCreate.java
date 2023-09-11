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
package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TenantUserReqCreate 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-06-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantUserReqCreate对象", description = "TenantUserReqCreate")
public class TenantUserReqCreate extends ReqCreate implements TenantUserVO {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "description")
  private String description;

  @ApiModelProperty(value = "userId")
  private String userId;

  @ApiModelProperty(value = "tenantId")
  private String tenantId;

  @ApiModelProperty(value = "type")
  private Integer type;

  @ApiModelProperty(value = "status")
  private Integer status;
}
