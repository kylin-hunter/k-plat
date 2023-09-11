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
package io.github.kylinhunter.plat.api.bean.vo.delete;

import com.google.common.collect.Lists;
import io.github.kylinhunter.plat.api.bean.vo.constants.ReqType;
import io.github.kylinhunter.plat.api.bean.vo.constants.VoType;
import io.github.kylinhunter.plat.api.bean.vo.request.Req;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 21:42
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReqDelete", description = "ReqDelete")
public class ReqDeletes extends Req implements Serializable {
  private static final long serialVersionUID = -8450405452557433712L;

  @ApiModelProperty(value = "physical", hidden = true, required = true)
  private boolean physical = true;

  @ApiModelProperty(value = "ids", required = true)
  @NotEmpty
  private Collection<String> ids;

  public ReqDeletes() {
    super(VoType.DELETE, ReqType.DELETE);
  }

  public ReqDeletes(Collection<String> ids) {
    this();
    this.ids = ids;
  }

  public static ReqDeletes of(String idArr) {
    List<String> allIds = Lists.newArrayList();
    String[] ids = StringUtils.split(idArr, ',');
    for (String id : ids) {
      if (!StringUtils.isEmpty(id)) {
        allIds.add(id);
      }
    }
    return new ReqDeletes(allIds);
  }

  public boolean isBatch() {

    return ids != null && ids.size() > 0;
  }
}
