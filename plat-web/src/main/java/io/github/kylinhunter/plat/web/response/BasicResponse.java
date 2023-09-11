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
package io.github.kylinhunter.plat.web.response;

import io.github.kylinhunter.commons.exception.info.ErrInfos;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yangchaosan
 * @date 2021/12/28 16:47
 */
@Getter
@Setter
@ApiModel("返回basicbean")
public class BasicResponse<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  @ApiModelProperty("业务状态码 0正常  非0错误")
  private int code = ErrInfos.CODE_SUCCESS;

  @ApiModelProperty("业务处理描述")
  private String msg;

  @ApiModelProperty("业务结果")
  private T data;

  public BasicResponse() {}

  public BasicResponse(T data) {
    this.data = data;
  }

  public BasicResponse(String msg) {
    this.msg = msg;
  }

  public BasicResponse(int code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public BasicResponse(int code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }
}
