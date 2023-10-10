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
package io.github.kylinhunter.plat.api.context;

import io.github.kylinhunter.plat.api.auth.Token;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-05-11 02:55
 */
@Data
public class DummyUserContext implements UserContext, Serializable {

  @Override
  public String getTenantId() {
    return null;
  }

  @Override
  public String getUserId() {
    return null;
  }

  @Override
  public Token getToken() {
    return null;
  }

  @Override
  public String getUserCode() {
    return null;
  }

  @Override
  public String getUserName() {
    return null;
  }

  @Override
  public int getUserType() {
    return 0;
  }


  @Override
  public void setTenantId(String tenantId) {
  }

  @Override
  public void setUserId(String userId) {
  }

  @Override
  public void setUserCode(String userCode) {
  }

  @Override
  public void setUserName(String userName) {
  }

  @Override
  public void setUserType(int type) {
  }

  @Override
  public boolean isDummy() {
    return true;
  }

  @Override
  public boolean isSuperAdmin() {
    return false;
  }
}
