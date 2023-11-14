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
package io.github.kylinhunter.plat.server.search.msg;

import io.github.kylinhunter.plat.data.kafka.KafkaSender;
import io.github.kylinhunter.plat.server.search.config.SearchConfig;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-09 01:15
 */
@Component
public class MsgSender extends KafkaSender {

  private SearchConfig searchConfig;

  public MsgSender(KafkaTemplate<String, Object> kafkaTemplate, SearchConfig searchConfig) {
    super(kafkaTemplate);
    this.searchConfig = searchConfig;
  }

  public void send(String message) {
    this.send(searchConfig.getTopic(), message);
  }
}
