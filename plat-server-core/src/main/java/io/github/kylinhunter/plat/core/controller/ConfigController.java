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
package io.github.kylinhunter.plat.core.controller;

import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author BiJi'an
 * @description curl -X POST 'http://127.0.0.1:8848/nacos/v1/cs/configs' \ -d
 *     "type=yaml&dataId=plat-server-core.yaml&group=DEFAULT_GROUP\ &content=test: test1: true
 *     test2: true"
 * @date 2022-06-03 23:14
 */
@RestController
@RequestMapping("/config")
@RefreshScope
public class ConfigController {

  @Value("${test.test1:false}")
  private String test1;

  @Value("${test.test2:false}")
  private String test2;

  @RequestMapping("/get")
  @ResponseBody
  public List<String> get() {
    return Lists.newArrayList(test1, test2);
  }
}
