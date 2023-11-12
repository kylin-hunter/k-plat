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
package io.github.kylinhunter.plat.api.page;

import io.github.kylinhunter.commons.collections.ListUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 18:53
 */
@ApiModel(value = "page_data")
@Data
@NoArgsConstructor
public class PageData<T> implements java.io.Serializable {
  @ApiModelProperty(value = "page_num")
  protected long pn = 1;

  @ApiModelProperty(value = "page_size")
  protected long ps = 10;

  @ApiModelProperty(value = "all_pages")
  protected long pages = 1;

  @ApiModelProperty(value = "totle_num")
  protected long total = 0;

  @ApiModelProperty(value = "start")
  protected long start;

  @ApiModelProperty(value = "end")
  protected long end;

  @ApiModelProperty(value = "body")
  protected List<T> body = ListUtils.newArrayList();

  public PageData(PageData<?> pageData) {
    this.pn = pageData.pn;
    this.ps = pageData.ps;
    this.pages = pageData.pages;
    this.total = pageData.total;
    this.start = pageData.start;
    this.end = pageData.end;
  }

  public void setTotal(long total) {
    this.total = total;
    cal();
  }

  public void addData(T t) {
    body.add(t);
  }

  private void cal() {

    int totalPage = (int) (total / ps + ((total % ps == 0) ? 0 : 1));
    if (totalPage <= 0) {
      totalPage = 1;
    }
    pages = totalPage;
    if (pn > totalPage) {
      pn = totalPage;
    } else if (pn <= 0) {
      pn = 1;
    }

    this.start = pn > 0 ? (pn - 1) * ps : 0;
    this.end = pn * ps;
    if (this.end > this.total) {
      this.end = this.total;
    }
  }
}
