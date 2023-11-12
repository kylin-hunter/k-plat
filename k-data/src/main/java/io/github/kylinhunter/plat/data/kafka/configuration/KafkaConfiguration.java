package io.github.kylinhunter.plat.data.kafka.configuration;

import io.github.kylinhunter.plat.data.config.KafkaConfig;
import io.github.kylinhunter.plat.data.config.RedisConfig;
import io.github.kylinhunter.plat.data.kafka.KafkaListenerManager;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.redisson.spring.starter.RedissonProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.context.support.GenericWebApplicationContext;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-09 02:08
 */
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(
    prefix = "kplat",
    value = "data.kafka.enabled",
    havingValue = "true"
)
@EnableConfigurationProperties({
    KafkaConfig.class
})
public class KafkaConfiguration {

  private final KafkaConfig dataConfig;
  private final GenericWebApplicationContext context;

  private final KafkaAdmin kafkaAdmin;

  @PostConstruct
  private void initTopics() {
    dataConfig.getInitTopics().forEach(topic -> {
      context.registerBean(topic.getName(), NewTopic.class,
          new Object[]{topic.getName(), topic.getNumPartitions(), topic.getReplicationFactor()});
    });
  }

  @Bean
  public KafkaListenerManager kafkaListenerManager(KafkaListenerEndpointRegistry registry) {
    return new KafkaListenerManager(registry);
  }
}