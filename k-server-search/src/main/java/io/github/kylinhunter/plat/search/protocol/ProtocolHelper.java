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
package io.github.kylinhunter.plat.search.protocol;

import io.github.kylinhunter.commons.utils.json.JsonOptions;
import io.github.kylinhunter.commons.utils.json.JsonUtils;
import io.github.kylinhunter.plat.search.index.bean.IndexBean;
import java.util.List;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-11 23:41
 */
public class ProtocolHelper {

  public static <T extends IndexBean> List<T> toIndex(String content, Class<T> clazz) {
    return JsonUtils.readToListObject(content, clazz, JsonOptions.NO_FAIL_SNAKE);
  }

  public static String toString(Object obj) {
    return JsonUtils.writeToString(obj, JsonOptions.NO_FAIL_SNAKE);
  }
}
