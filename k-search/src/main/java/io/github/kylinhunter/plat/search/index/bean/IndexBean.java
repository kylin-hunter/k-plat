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
package io.github.kylinhunter.plat.search.index.bean;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-10 01:23
 */
@Data
@RequiredArgsConstructor
public class IndexBean {

  @Id protected String id;

  //  @Field(type = FieldType.Keyword, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
  @Field(type = FieldType.Keyword)
  private String sysTenantId;

  @Field(type = FieldType.Keyword)
  private String sysCreatedUserId;

  @Field(type = FieldType.Text)
  private String sysCreatedUserName;

  @Field(type = FieldType.Date, format = DateFormat.date_time)
  private LocalDateTime sysCreatedTime;

  @Field(type = FieldType.Keyword)
  private String sysUpdateUserId;

  @Field(type = FieldType.Text)
  private String sysUpdateUserName;

  @Field(type = FieldType.Date, format = DateFormat.date_time)
  private LocalDateTime sysUpdateTime;

  @Field(type = FieldType.Boolean, format = DateFormat.date_time)
  private boolean sysDeleteFlag;
}
