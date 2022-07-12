package com.kylinhunter.plat.data.configuration.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-12 00:22
 **/

@Configuration
@ConditionalOnProperty(prefix = "app", value = "data.redis.enabled", havingValue = "true")
public class RedissonConfiguration {

}