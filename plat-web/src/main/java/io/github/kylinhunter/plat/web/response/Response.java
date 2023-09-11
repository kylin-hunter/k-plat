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
package io.github.kylinhunter.plat.web.response;

import io.github.kylinhunter.plat.web.trace.explain.TraceExplain;
import java.io.Serializable;

/**
 * @description 返回响应
 * @author BiJi'an
 * @date 2022/01/01
 */
public interface Response<T> extends Serializable {
  int getCode();

  void setCode(int code);

  String getMsg();

  void setMsg(String msg);

  T getData();

  void setData(T data);

  long getTime();

  void setTime(long time);

  String getStartTime();

  void setStartTime(String startTime);

  String getEndTime();

  void setEndTime(String endTime);

  long getDurationTime();

  void setDurationTime(long durationTime);

  String getTraceId();

  void setTraceId(String traceId);

  TraceExplain getTraceExplain();

  void setTraceExplain(TraceExplain traceExplain);
}
