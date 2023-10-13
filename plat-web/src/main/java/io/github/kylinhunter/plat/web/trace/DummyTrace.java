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
package io.github.kylinhunter.plat.web.trace;

import io.github.kylinhunter.commons.lang.strings.StringConst;
import io.github.kylinhunter.plat.api.auth.Token;
import io.github.kylinhunter.plat.api.auth.VerifyToken;
import io.github.kylinhunter.plat.api.auth.context.DummyUserContext;
import io.github.kylinhunter.plat.api.auth.context.UserContext;
import io.github.kylinhunter.plat.api.trace.Trace;
import io.github.kylinhunter.plat.web.trace.explain.DummyTraceExplain;
import io.github.kylinhunter.plat.api.trace.TraceExplain;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-11 02:14
 */
public class DummyTrace implements Trace {

  private final DummyTraceExplain dummyTraceExplain = new DummyTraceExplain();
  private static final UserContext DUMMY_USER_CONTEXT = new DummyUserContext();

  @Override
  public String getId() {
    return StringConst.EMPTY;
  }

  @Override
  public void setId(String id) {
  }

  @Override
  public boolean isDebug() {
    return false;
  }

  @Override
  public void setDebug(boolean debug) {

  }

  @Override
  public UserContext getUserContext() {
    return DUMMY_USER_CONTEXT;
  }

  @Override
  public VerifyToken getVerifyToken() {
    return null;
  }

  @Override
  public String getToken() {
    return null;
  }

  @Override
  public void setVerifyToken(VerifyToken verifyToken) {

  }



  @Override
  public void setUserContext(UserContext userContext) {

  }


  @Override
  public void setToken(String token) {
  }

  @Override
  public long getStartTime() {
    return 0;
  }

  @Override
  public long getEndTime() {
    return 0;
  }

  @Override
  public Trace end() {
    return this;
  }

  @Override
  public long getDurationTime() {
    return 0;
  }

  @Override
  public void setExplain(TraceExplain explain) {
  }

  @Override
  public TraceExplain getExplain() {
    return dummyTraceExplain;
  }

  @Override
  public boolean isDummy() {
    return true;
  }
}
