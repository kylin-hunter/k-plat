package io.github.kylinhunter.plat.search.controller;

import java.util.Arrays;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.context.support.GenericWebApplicationContext;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-09 02:08
 */
@Configuration
@RequiredArgsConstructor
public class KafkaInitialConfiguration {

  @Autowired
  private KafkaAdmin kafkaAdmin;

  //创建TopicName为topic-new的Topic并设置分区数为4以及副本数为2
  @Bean
  public NewTopic initialTopic(KafkaAdmin kafkaAdmin) {
    NewTopic newTopic = new NewTopic("topic-444", 2, (short) 1);
 KafkaAdminClient kafkaAdminClient = (KafkaAdminClient) KafkaAdminClient.create(kafkaAdmin.getConfigurationProperties());
//    CreateTopicsResult topics = kafkaAdminClient.createTopics(Arrays.asList(newTopic));
//    kafkaAdmin.createOrModifyTopics(newTopic);
//    AdminClient.create()

    return newTopic;
  }

  private final GenericWebApplicationContext context;

  @PostConstruct
  private void createKafkaTopic() {
    NewTopic newTopic = new NewTopic("xxxx", 1, (short) 0);

    context.registerBean("bbb111", NewTopic.class, new Object[]{"bbb111", 1, (short) 1});

  }
}