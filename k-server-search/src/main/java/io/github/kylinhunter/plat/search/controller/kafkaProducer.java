package io.github.kylinhunter.plat.search.controller;

import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.GenericWebApplicationContext;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-09 01:15
 */
@RestController
@RequiredArgsConstructor
public class kafkaProducer {

  @Autowired
  private KafkaTemplate<String, Object> kafkaTemplate;
  private final GenericWebApplicationContext context;

  @Autowired
  private KafkaAdmin kafkaAdmin;
  @GetMapping("/kafka/normal/{message}")
  public void sendNormalMessage(@PathVariable("message") String message) {
    kafkaTemplate.send("sb_topic666", message);
  }

  @PostConstruct
  private void createKafkaTopic() {
    NewTopic newTopic = new NewTopic("", 1, (short) 0);

    context.registerBean("bbb", NewTopic.class,  new Object[]{"bbb", 1, (short) 0});

  }

}