package io.github.kylinhunter.plat.search.msg;

import io.github.kylinhunter.plat.data.kafka.KafkaSender;
import io.github.kylinhunter.plat.search.config.SearchConfig;
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