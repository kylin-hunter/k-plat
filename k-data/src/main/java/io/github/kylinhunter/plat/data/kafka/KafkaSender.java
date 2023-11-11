package io.github.kylinhunter.plat.data.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-09 01:15
 */

@RequiredArgsConstructor
@Component
public class KafkaSender {

  private final KafkaTemplate<String, Object> kafkaTemplate;


  public void send(String topic, String message) {
    kafkaTemplate.send(topic, message);
  }


}