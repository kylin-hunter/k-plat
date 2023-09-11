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
package io.github.kylinhunter.plat.api.bean.vo.request;

import io.github.kylinhunter.plat.api.bean.vo.BasicVO;
import io.github.kylinhunter.plat.api.bean.vo.VO;
import io.github.kylinhunter.plat.api.bean.vo.constants.ReqType;
import io.github.kylinhunter.plat.api.bean.vo.constants.VoType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 21:42
 */
@Setter
@Getter
@ApiModel(value = "Req", description = "Req")
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Req extends BasicVO implements VO {
  private static final long serialVersionUID = -8450405452557433712L;

  @ApiModelProperty(value = "debug", hidden = true)
  private boolean debug = false;

  @ApiModelProperty(value = "reqType", hidden = true)
  private ReqType reqType;

  public Req(VoType voType, ReqType reqType) {
    super(voType);
    this.reqType = reqType;
  }

  public void copyFrom(Req req) {
    this.debug = req.debug;
  }
}
