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
package io.github.kylinhunter.plat.api.bean.vo.query;

import com.google.common.collect.Lists;
import io.github.kylinhunter.commons.exception.embed.ParamException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 21:42
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "ReqByIds", description = "ReqByIds")
@NoArgsConstructor
@AllArgsConstructor
public class ReqByIds extends ReqQuery implements Serializable {
  

  @ApiModelProperty(value = "primary keys", required = true)
  @NotEmpty
  private Collection<String> ids;

  public static ReqByIds of(String idArr) {
    List<String> allIds = Lists.newArrayList();
    String[] ids = StringUtils.split(idArr, ',');
    for (String id : ids) {
      if (!StringUtils.isEmpty(id)) {
        allIds.add(id);
      }
    }
    if (allIds.size() > 100) {
      throw new ParamException(" limit: " + 100);
    }
    return new ReqByIds(allIds);
  }
}
