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
package io.github.kylinhunter.plat.data.redis.configuration;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import io.github.kylinhunter.plat.data.config.RedisConfig;
import io.github.kylinhunter.plat.data.redis.service.RedisService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@Slf4j
@ConditionalOnProperty(
    prefix = "kplat",
    value = "data.redis.enabled",
    havingValue = "true",
    matchIfMissing = true)
public class RedisTemplateConfiguration {

  @Resource private RedisConnectionFactory redisConnectionFactory;

  @Bean
  @Primary
  public RedisTemplate<String, Object> redisTemplate() {
    final GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer =
        new GenericJackson2JsonRedisSerializer(createObjectMapper());

    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(genericJackson2JsonRedisSerializer);
    template.setHashKeySerializer(new StringRedisSerializer());
    template.setHashValueSerializer(genericJackson2JsonRedisSerializer);
    template.setConnectionFactory(redisConnectionFactory);
    log.info(" redisTemplate with redisConnectionFactory:" + redisConnectionFactory);
    return template;
  }

  @Bean
  @Primary
  public RedisService redisService(
      @Autowired RedisTemplate<String, Object> redisTemplate,
      RedisConfig kplatDataRedisConfig) {
    return new RedisService(redisTemplate, kplatDataRedisConfig);
  }

  @Bean("redisTemplateJDK")
  public RedisTemplate<String, Object> redisTemplateJDK() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setKeySerializer(new StringRedisSerializer());
    template.setHashKeySerializer(new StringRedisSerializer());
    template.setConnectionFactory(redisConnectionFactory);
    return template;
  }

  @Bean("redisServiceJDK")
  public RedisService redisServiceJDK(
      @Autowired @Qualifier("redisTemplateJDK") RedisTemplate<String, Object> redisTemplate,
      RedisConfig kplatDataRedisConfig) {
    return new RedisService(redisTemplate, kplatDataRedisConfig);
  }

  public static ObjectMapper createObjectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    objectMapper.configure(MapperFeature.USE_ANNOTATIONS, false);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    // 此项必须配置，否则会报java.lang.ClassCastException: java.util.LinkedHashMap cannot be cast to XXX
    //        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL,
    // JsonTypeInfo.As.PROPERTY);
    objectMapper.activateDefaultTyping(
        LaissezFaireSubTypeValidator.instance,
        ObjectMapper.DefaultTyping.NON_FINAL,
        JsonTypeInfo.As.WRAPPER_ARRAY);
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    JavaTimeModule javaTimeModule = new JavaTimeModule();

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    javaTimeModule.addSerializer(
        LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormatter));
    javaTimeModule.addDeserializer(
        LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormatter));

    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormatter));
    javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormatter));

    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(timeFormatter));
    javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(timeFormatter));

    objectMapper.registerModule(javaTimeModule);
    return objectMapper;
  }


}
