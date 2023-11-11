package io.github.kylinhunter.plat.search.bean.index;

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