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
package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.constants.ReqType;
import io.github.kylinhunter.plat.api.bean.vo.request.Req;
import io.github.kylinhunter.plat.api.module.core.constants.TenantCatalogType;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-18 11:29
 */
@ToString
@Getter
@Setter
public class TenantCatalogReqCreateBatch extends Req {

  private String code;
  private String name;
  private int level = 0;
  private int sort = 0;
  private int type = TenantCatalogType.DEFAULT.getCode();
  private String parentCode;
  private List<TenantCatalogReqCreateBatch> children;
  boolean force = false;

  public TenantCatalogReqCreateBatch() {
    super(ReqType.OTHER);
  }
}
