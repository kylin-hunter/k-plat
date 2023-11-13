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

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * TenantCatalogReqUpdate 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-06-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "TenantCatalogReqUpdate", description = "TenantCatalogReqUpdate")
public class TenantCatalogReqUpdate extends ReqUpdate implements TenantCatalogVO {

  @ApiModelProperty(value = "description")
  private String description;

  @ApiModelProperty(value = "parent_id")
  private String parentId;

  @ApiModelProperty(value = "name", hidden = true)
  private String name;

  @ApiModelProperty(value = "code")
  @JsonIgnore
  private String code;

  @ApiModelProperty(value = "path", hidden = true)
  @JsonIgnore
  private Integer level;

  @ApiModelProperty(value = "sort", hidden = true)
  @JsonIgnore
  private Integer sort;

  @ApiModelProperty(value = "type", hidden = true)
  @JsonIgnore
  private Integer type;

  @ApiModelProperty(value = "path", hidden = true)
  @JsonIgnore
  private String path;

  @ApiModelProperty(value = "status", hidden = true)
  @JsonIgnore
  private Integer status;
}