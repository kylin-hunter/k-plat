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
package io.github.kylinhunter.plat.api.auth;

import io.github.kylinhunter.plat.api.context.UserContext;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 01:49
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Token  implements UserContext {


  private String userId = "";
  private String userName = "";

  private String nickName = "";
  private String realName = "";


  private String tenantId = "";
  private String tenantUserId = "";
  private int userType;
  private long effectiveTime;
  private LocalDateTime expireTime;
}
