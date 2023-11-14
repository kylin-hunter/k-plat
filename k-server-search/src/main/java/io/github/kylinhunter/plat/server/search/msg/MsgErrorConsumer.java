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

import java.util.List;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-09 01:16
 */
@Component
public class MsgErrorConsumer {

  // 监听消费
  @KafkaListener(
      id = "${kplat.search.topic-error}_id",
      topics = {"${kplat.search.topic-error}"},
      groupId = "${kplat.search.topic-error}_group")
  public void onNormalMessage(
      List<ConsumerRecord<String, Object>> records, Acknowledgment acknowledgment) {
    records.forEach(
        record -> {
          System.out.println(
              "简单消费：" + record.topic() + "-" + record.partition() + "=" + record.value());
        });
    acknowledgment.acknowledge();
  }
}
