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
package io.github.kylinhunter.plat.data.redis.service;

import io.github.kylinhunter.commons.util.ObjectValues;
import io.github.kylinhunter.plat.api.middleware.redis.RedisKey;
import io.github.kylinhunter.plat.data.config.RedisConfig;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.core.script.RedisScript;

@RequiredArgsConstructor
public class RedisService {

  protected final RedisTemplate<String, Object> redisTemplate;

  private final RedisConfig kplatDataRedisConfig;

  @PostConstruct
  private void init() {
    RedisKey.setNamespace(kplatDataRedisConfig.getNamespace());
  }

  /**
   * @param key key
   * @return java.lang.Boolean
   * @title delete
   * @description delete
   * @author BiJi'an
   * @date 2023-10-09 22:52
   */
  public Boolean delete(String key) {

    if (!StringUtils.isBlank(key)) {
      return redisTemplate.delete(key);
    }
    return false;
  }

  /**
   * @param key key
   * @return java.lang.Boolean
   * @title hasKey
   * @description hasKey
   * @author BiJi'an
   * @date 2023-10-09 22:52
   */
  public Boolean hasKey(String key) {
    return redisTemplate.hasKey(key);
  }

  /**
   * @return org.springframework.data.redis.core.ValueOperations<java.lang.String, java.lang.Object>
   * @title opsForValue
   * @description opsForValue
   * @author BiJi'an
   * @date 2023-10-09 22:52
   */
  public ValueOperations<String, Object> opsForValue() {
    return redisTemplate.opsForValue();
  }

  /**
   * @param key key
   * @param value value
   * @return void
   * @title set
   * @description set
   * @author BiJi'an
   * @date 2023-10-09 22:52
   */
  public void set(String key, Object value) {

    redisTemplate.opsForValue().set(key, value);
  }

  /**
   * @param key key
   * @param values values
   * @param expireSecond expireSecond
   * @return void
   * @title set
   * @description set
   * @author BiJi'an
   * @date 2023-10-09 22:52
   */
  public void set(String key, Object values, long expireSecond) {

    redisTemplate.opsForValue().set(key, values, expireSecond, TimeUnit.SECONDS);
  }

  /**
   * @param key key
   * @param value value
   * @param timeout timeout
   * @param unit unit
   * @return void
   * @title set
   * @description set
   * @author BiJi'an
   * @date 2023-10-09 22:52
   */
  public void set(String key, Object value, long timeout, TimeUnit unit) {
    redisTemplate.opsForValue().set(key, value, timeout, unit);
  }

  /**
   * @param key key
   * @param value value
   * @return java.lang.Long
   * @title increment
   * @description increment
   * @author BiJi'an
   * @date 2023-10-09 22:52
   */
  public Long increment(String key, long value) {
    return this.increment(key, Long.valueOf(value));
  }

  /**
   * @param key key
   * @param value value
   * @return java.lang.Long
   * @title increment
   * @description increment
   * @author BiJi'an
   * @date 2023-10-09 22:52
   */
  public Long increment(String key, Long value) {
    return redisTemplate.opsForValue().increment(key, value);
  }

  /**
   * @param key key
   * @return T
   * @title get
   * @description get
   * @author BiJi'an
   * @date 2023-10-09 22:52
   */
  @SuppressWarnings("unchecked")
  public <T> T get(String key) {
    return (T) redisTemplate.opsForValue().get(key);
  }

  /**
   * @param key key
   * @param defaultValue defaultValue
   * @return java.lang.Long
   * @title getLong
   * @description getLong
   * @author BiJi'an
   * @date 2023-10-09 22:52
   */
  @SuppressWarnings("unchecked")
  public Long getLong(String key, Long defaultValue) {
    return ObjectValues.getLong(redisTemplate.opsForValue().get(key), defaultValue);
  }

  /**
   * @return org.springframework.data.redis.core.SetOperations<java.lang.String, java.lang.Object>
   * @title opsForSet
   * @description opsForSet
   * @author BiJi'an
   * @date 2023-10-09 22:53
   */
  public SetOperations<String, Object> opsForSet() {
    return redisTemplate.opsForSet();
  }

  /**
   * @param key key
   * @param value value
   * @return void
   * @title forSetAdd
   * @description forSetAdd
   * @author BiJi'an
   * @date 2023-10-09 22:53
   */
  public void forSetAdd(String key, Object value) {
    redisTemplate.opsForSet().add(key, value);
  }

  /**
   * @param key key
   * @return T
   * @title forSetPop
   * @description forSetPop
   * @author BiJi'an
   * @date 2023-10-09 22:53
   */
  public <T> T forSetPop(String key) {
    return (T) redisTemplate.opsForSet().pop(key);
  }

  /**
   * @param key key
   * @return java.lang.Long
   * @title forSetSize
   * @description forSetSize
   * @author BiJi'an
   * @date 2023-10-09 22:53
   */
  public Long forSetSize(String key) {
    return redisTemplate.opsForSet().size(key);
  }

  /**
   * @return org.springframework.data.redis.core.ZSetOperations<java.lang.String, java.lang.Object>
   * @title opsForZSet
   * @description opsForZSet
   * @author BiJi'an
   * @date 2023-10-09 22:53
   */
  public ZSetOperations<String, Object> opsForZSet() {
    return redisTemplate.opsForZSet();
  }

  /**
   * @param key key
   * @param member member
   * @param value value
   * @return void
   * @title forZSetAdd
   * @description forZSetAdd
   * @author BiJi'an
   * @date 2023-10-09 22:53
   */
  public void forZSetAdd(String key, String member, double value) {
    redisTemplate.opsForZSet().add(key, member, value);
  }

  /**
   * @param key key
   * @param member member
   * @param value value
   * @return void
   * @title forZSetIncrementScore
   * @description forZSetIncrementScore
   * @author BiJi'an
   * @date 2023-10-09 22:53
   */
  public void forZSetIncrementScore(String key, String member, double value) {
    redisTemplate.opsForZSet().incrementScore(key, member, value);
  }

  /**
   * @param key key
   * @return java.lang.Long
   * @title forZSetSize
   * @description forZSetSize
   * @author BiJi'an
   * @date 2023-10-09 22:53
   */
  public Long forZSetSize(String key) {
    return redisTemplate.opsForZSet().size(key);
  }

  /**
   * @param key key
   * @param values values
   * @return T
   * @title forSetRemove
   * @description forSetRemove
   * @author BiJi'an
   * @date 2023-10-09 22:58
   */
  public <T> T forZSetRemove(String key, Object... values) {
    return (T) redisTemplate.opsForZSet().remove(key, values);
  }

  /**
   * @param key key
   * @param member member
   * @return java.lang.Double
   * @title forZSetScore
   * @description forZSetScore
   * @author BiJi'an
   * @date 2023-10-09 22:53
   */
  public Double forZSetScore(String key, String member) {
    return redisTemplate.opsForZSet().score(key, member);
  }

  /**
   * @param redisScript redisScript
   * @param keys keys
   * @param args args
   * @return T
   * @title executeLuaScript
   * @description executeLuaScript
   * @author BiJi'an
   * @date 2023-10-09 22:53
   */
  public <T> T execute(RedisScript<T> redisScript, List<String> keys, Object... args) {
    return redisTemplate.execute(redisScript, keys, args);
  }
}
