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
package io.github.kylinhunter.plat.server.search.index.bean;

import io.github.kylinhunter.plat.search.index.bean.IndexBean;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-10 01:23
 */
@Document(indexName = "employee_info", shards = 1, replicas = 0)
@Getter
@Setter
public class User extends IndexBean {

  @Field(type = FieldType.Text)
  private String nickName;

  @Field(type = FieldType.Text)
  private String realName;

  @Field(type = FieldType.Text)
  private String userName;

  @Field(type = FieldType.Keyword)
  private String source;

  @Field(type = FieldType.Keyword)
  private int type;

  @Field(type = FieldType.Keyword)
  private int status;

  @Field(type = FieldType.Text)
  private String description;
}
