package io.github.kylinhunter.plat.search.controller;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-09 01:16
 */
@Component
public class KafkaConsumer {

  //监听消费
  @KafkaListener(topics = {"topic-999"})
  public void onNormalMessage(ConsumerRecord<String, Object> record) {
    System.out.println("简单消费：" + record.topic() + "-" + record.partition() + "=" +
        record.value());
  }

}