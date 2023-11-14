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
package io.github.kylinhunter.plat.search.index.msg;

import io.github.kylinhunter.commons.utils.json.JsonOptions;
import io.github.kylinhunter.commons.utils.json.JsonUtils;
import io.github.kylinhunter.plat.search.index.bean.IndexBean;
import java.util.List;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-11 23:41
 */
public class IndexMsgHelper {

  /**
   * @param obj obj
   * @return java.lang.String
   * @throws
   * @title toString
   * @description toString
   * @author BiJi'an
   * @date 2023-11-15 00:58
   */
  public static <T extends IndexBean> String toString(List<T> obj) {
    return JsonUtils.writeToString(obj, JsonOptions.NO_FAIL_SNAKE);
  }

  /**
   * @param obj obj
   * @return java.lang.String
   * @throws
   * @title toString
   * @description toString
   * @author BiJi'an
   * @date 2023-11-15 00:58
   */
  public static <T extends IndexBean> String toString(T obj) {
    return JsonUtils.writeToString(obj, JsonOptions.NO_FAIL_SNAKE);
  }

  /**
   * @param content content
   * @param clazz   clazz
   * @return java.util.List<T>
   * @throws
   * @title toBeans
   * @description toBeans
   * @author BiJi'an
   * @date 2023-11-15 00:58
   */
  public static <T extends IndexBean> List<T> toBeans(String content, Class<T> clazz) {
    return JsonUtils.readToListObject(content, clazz, JsonOptions.NO_FAIL_SNAKE);
  }

  /**
   * @param content content
   * @param clazz   clazz
   * @return T
   * @throws
   * @title toBean
   * @description toBean
   * @author BiJi'an
   * @date 2023-11-15 00:58
   */
  public static <T extends IndexBean> T toBean(String content, Class<T> clazz) {
    return JsonUtils.readToObject(content, clazz, JsonOptions.NO_FAIL_SNAKE);
  }
}
