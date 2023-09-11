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

import com.google.common.collect.Lists;
import io.github.kylinhunter.plat.api.bean.vo.BasicVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangchaosan
 * @date 2022/1/5 11:35
 */
@Data
@ApiModel(value = "BatchResp", description = "BatchResp")
@NoArgsConstructor
public class BatchResp<T extends BasicVO> implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty(value = "body")
  private List<BatchSingleResp<T>> body = Lists.newArrayList();

  public void addSingleResp(T singleResp) {
    this.body.add(new BatchSingleResp<>(singleResp));
  }

  public void addSingleResp(int code, String msg, T singleResp) {
    this.body.add(new BatchSingleResp<>(code, msg, singleResp));
  }
}
