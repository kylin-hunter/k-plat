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
package io.github.kylinhunter.plat.api.bean.vo.update;

import io.github.kylinhunter.plat.api.bean.vo.constants.ReqType;
import io.github.kylinhunter.plat.api.bean.vo.constants.VoType;
import io.github.kylinhunter.plat.api.bean.vo.request.Req;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author BiJi'an
 * @description
 * @date 2022/5/12
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@ApiModel(value = "ReqUpdate", description = "ReqUpdate")
public class ReqUpdate extends Req {
  @ApiModelProperty(value = "primary key")
  @EqualsAndHashCode.Include
  private String id;

  public ReqUpdate() {
    super(VoType.UPDATE, ReqType.UPDATE);
  }

  public ReqUpdate(String id) {
    this();
    this.id = id;
  }
}
