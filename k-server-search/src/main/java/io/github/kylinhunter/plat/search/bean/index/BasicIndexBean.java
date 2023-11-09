package io.github.kylinhunter.plat.search.bean.index;

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
public class BasicIndexBean {

  @Id
  protected  String id;

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