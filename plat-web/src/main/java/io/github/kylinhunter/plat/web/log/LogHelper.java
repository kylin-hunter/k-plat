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
package io.github.kylinhunter.plat.web.log;

import org.slf4j.MDC;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-30 21:05
 */
public class LogHelper {
  private static final String MDC_TRACE_ID = "x-trace-id";
  private static final String MDC_USER_ID = "x-current-user";

  public static void setTraceId(String traceId) {
    MDC.put(MDC_TRACE_ID, traceId);
  }

  public static void setMdcUserId(String userId) {
    MDC.put(MDC_USER_ID, userId);
  }

  public static void clearContext() {
    MDC.clear();
  }
}
