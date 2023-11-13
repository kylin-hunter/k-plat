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
package io.github.kylinhunter.plat.api.trace;

import io.github.kylinhunter.plat.api.auth.VerifyToken;
import io.github.kylinhunter.plat.api.auth.context.UserContext;
import io.github.kylinhunter.plat.api.trace.explain.DefaultTraceExplain;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-30 11:43
 */
@Getter
@Setter
@NoArgsConstructor
public class DefaultTrace implements Trace {

  private String id;
  private String token;

  private long startTime = System.currentTimeMillis();
  private long endTime = startTime;
  private long durationTime;

  private boolean debug;

  private TraceExplain explain = new DefaultTraceExplain();

  private VerifyToken verifyToken;

  private UserContext userContext;

  public DefaultTrace(String id, String token) {
    if (id != null && id.length() > 0) {
      this.id = id;
    } else {
      this.id = UUID.randomUUID().toString();
    }
    this.token = token;
  }

  @Override
  public UserContext getUserContext() {
    return verifyToken;
  }

  @Override
  public void setUserContext(UserContext userContext) {
    this.userContext = userContext;
    this.verifyToken = null;
  }

  @Override
  public VerifyToken getVerifyToken() {
    return verifyToken;
  }

  @Override
  public void setVerifyToken(VerifyToken verifyToken) {
    this.verifyToken = verifyToken;
    this.userContext = verifyToken;
  }

  @Override
  public Trace end() {
    this.endTime = System.currentTimeMillis();
    long cost = endTime - startTime;
    if (cost > 0) {
      this.durationTime = cost;
    }
    return this;
  }
}