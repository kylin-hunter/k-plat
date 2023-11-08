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
package io.github.kylinhunter.plat.search.config;

import io.github.kylinhunter.commons.exception.embed.InitException;
import io.github.kylinhunter.commons.lang.EnumUtils;
import io.github.kylinhunter.plat.api.module.storage.constants.StorageType;
import javax.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-27 01:24
 */
@Data
@ConfigurationProperties(prefix = "kplat.storage")
@Component
@Slf4j
@ToString
public class StorageConfig {
  @Getter(AccessLevel.NONE)
  private String type;

  private String a;
  private S3Config s3;
  private StorageType storageType;

  @PostConstruct
  private void init() {
    if (StringUtils.isEmpty(type)) {
      throw new InitException("init storage config error,type is emtpy");
    }
    storageType = EnumUtils.fromName(StorageType.class, type.toUpperCase());
    log.info("init storage config ={}", this.toString());
  }
}
