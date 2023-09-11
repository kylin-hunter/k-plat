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
package io.github.kylinhunter.plat.api.bean.vo.response.batch;

import io.github.kylinhunter.commons.exception.info.ErrInfos;
import io.github.kylinhunter.plat.api.bean.vo.BasicVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author BiJi'an
 * @description
 * @date 2022/4/14
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "BatchSingleResp", description = "BatchSingleResp")
@AllArgsConstructor
public class BatchSingleResp<T extends BasicVO> implements Serializable {

  @ApiModelProperty(value = "code")
  private int code = ErrInfos.CODE_SUCCESS;

  @ApiModelProperty(value = "msg")
  private String msg;

  @ApiModelProperty(value = "body")
  private T body;

  public BatchSingleResp(T body) {
    this.body = body;
  }
}
