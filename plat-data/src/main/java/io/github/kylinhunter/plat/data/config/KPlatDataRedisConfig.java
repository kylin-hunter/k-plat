package io.github.kylinhunter.plat.data.config;

import javax.annotation.PostConstruct;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * @author BiJi'an
 * @description
 * @date 2023-10-15 23:25
 */

@Data
@ToString
@Slf4j
@RefreshScope
@ConfigurationProperties(prefix = "kplat.data.redis")
public class KPlatDataRedisConfig {

  private String namespace="plat";

  @PostConstruct
  private void init() {
    log.info("init " + this.toString());
  }
}