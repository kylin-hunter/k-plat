package io.github.kylinhunter.plat.search.msg;

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
public class MsgConsumer {

  //监听消费
  @KafkaListener(topics = {"${kplat.search.topic}"})
  public void onNormalMessage(List<ConsumerRecord<String, Object>> records,
      Acknowledgment acknowledgment) {
    records.forEach(record->{
      System.out.println(
          "简单消费：" + record.topic() + "-" + record.partition() + "=" + record.value());
    });
    acknowledgment.acknowledge();

  }

}