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
package io.github.kylinhunter.plat.api.module.storage.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * FileRelationReqUpdate 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-07-05
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "FileRelationReqUpdate对象", description = "FileRelationReqUpdate")
public class FileRelationReqUpdate extends ReqUpdate implements FileRelationVO {

  

  @ApiModelProperty(value = "type")
  private Integer type;

  @ApiModelProperty(value = "masterId")
  private String masterId;

  @ApiModelProperty(value = "fileId")
  private String fileId;
}
