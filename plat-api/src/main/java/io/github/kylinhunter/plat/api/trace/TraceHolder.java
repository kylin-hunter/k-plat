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

import javax.servlet.http.HttpServletRequest;

/**
 * @author BiJi'an
 * @description 日志追踪
 * @date 2022/01/01
 */
public interface TraceHolder {
  /**
   * @return io.github.kylinhunter.plat.commons.trace.Trace
   * @throws
   * @title 隐式 从 request中 创建 trace
   * @description
   * @author BiJi'an
   * @date 2022/01/01 2:46 下午
   */
  Trace create(HttpServletRequest request);

  /**
   * @return io.github.kylinhunter.plat.commons.trace.Trace
   * @throws
   * @title 获取trace
   * @description
   * @author BiJi'an
   * @date 2021/7/30 11:25 上午
   */
  Trace get();

  /**
   * @return 清空trace
   * @throws
   * @title remove
   * @description
   * @author BiJi'an
   * @date 2022/01/01 2:54 下午
   */
  void remove();
}
