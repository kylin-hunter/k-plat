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

import java.util.List;

public interface UserContext {

  String getTenantId();

  String getUserId();

  String getUserCode();

  String getUserName();

  int getUserType();

  List<String> getRoleIds();

  List<String> getRoleCodes();

  void setTenantId(final String tenantId);

  void setUserId(final String userId);

  void setUserCode(final String userCode);

  void setUserName(final String userName);

  void setUserType(final int type);

  void setRoleIds(final List<String> roleIds);

  void setRoleCodes(final List<String> roleCodes);

  boolean isDummy();

  boolean isSuperAdmin();
}
