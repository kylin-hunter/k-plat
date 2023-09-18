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
package io.github.kylinhunter.plat.data.configuration.redis;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties.Cluster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-12 00:22
 */
@Configuration
@ConditionalOnProperty(prefix = "plat", value = "data.redis.enabled", havingValue = "true")
public class RedissonConfiguration {

  private static final String REDIS_PROTOCOL_PREFIX = "redis://";
  private static final String REDISS_PROTOCOL_PREFIX = "rediss://";
  @Autowired
  private RedisProperties redisProperties;

  @Bean
  public RedissonClient r1edissonClient() {

    Cluster cluster = redisProperties.getCluster();
    if (cluster != null && cluster.getNodes().size() > 0) {
      return getClusterRedisson();
    } else {
      return getSingleRedisson();
    }

  }

  public RedissonClient getSingleRedisson() {
    Duration timeout = redisProperties.getTimeout();
    Duration connectTimeout = redisProperties.getConnectTimeout();
    String username = redisProperties.getUsername();
    String prefix = getPrefix();
    Config config = new Config();
    SingleServerConfig c = config.useSingleServer()
        .setAddress(prefix + redisProperties.getHost() + ":" + redisProperties.getPort())
        .setDatabase(redisProperties.getDatabase())
        .setUsername(username)
        .setPassword(redisProperties.getPassword())
        .setClientName(redisProperties.getClientName());
    if (connectTimeout != null) {
      c.setConnectTimeout((int) connectTimeout.toMillis());
    }
    if (timeout != null) {
      c.setTimeout((int) timeout.toMillis());
    }

    return Redisson.create(config);
  }

  public RedissonClient getClusterRedisson() {
    Cluster cluster = redisProperties.getCluster();
    List<String> nodesObject = cluster.getNodes();
    Duration timeout = redisProperties.getTimeout();
    Duration connectTimeout = redisProperties.getConnectTimeout();
    String username = redisProperties.getUsername();
    String prefix = getPrefix();

    Config config = new Config();
    String[] nodes = convert(prefix, nodesObject);

    ClusterServersConfig c = config.useClusterServers()
        .addNodeAddress(nodes)
        .setUsername(redisProperties.getUsername())
        .setPassword(redisProperties.getPassword())
        .setClientName(redisProperties.getClientName());
    if (connectTimeout != null) {
      c.setConnectTimeout((int) connectTimeout.toMillis());
    }
    if (timeout != null) {
      c.setTimeout((int) timeout.toMillis());
    }
    return Redisson.create(config);
  }

  private String[] convert(String prefix, List<String> nodesObject) {
    List<String> nodes = new ArrayList<>(nodesObject.size());
    for (String node : nodesObject) {
      if (!node.startsWith(REDIS_PROTOCOL_PREFIX) && !node.startsWith(REDISS_PROTOCOL_PREFIX)) {
        nodes.add(prefix + node);
      } else {
        nodes.add(node);
      }
    }
    return nodes.toArray(new String[0]);
  }

  private String getPrefix() {
    String prefix = REDIS_PROTOCOL_PREFIX;
    Method isSSLMethod = ReflectionUtils.findMethod(RedisProperties.class, "isSsl");
    Method getSSLMethod = ReflectionUtils.findMethod(RedisProperties.class, "getSsl");
    if (isSSLMethod != null) {
      if ((Boolean) ReflectionUtils.invokeMethod(isSSLMethod, redisProperties)) {
        prefix = REDISS_PROTOCOL_PREFIX;
      }
    } else if (getSSLMethod != null) {
      Object ss = ReflectionUtils.invokeMethod(getSSLMethod, redisProperties);
      if (ss != null) {
        Method isEnabledMethod = ReflectionUtils.findMethod(ss.getClass(), "isEnabled");
        Boolean enabled = (Boolean) ReflectionUtils.invokeMethod(isEnabledMethod, ss);
        if (enabled) {
          prefix = REDISS_PROTOCOL_PREFIX;
        }
      }
    }
    return prefix;
  }
}
