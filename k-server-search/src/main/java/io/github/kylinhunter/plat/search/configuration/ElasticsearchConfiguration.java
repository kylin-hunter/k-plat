package io.github.kylinhunter.plat.search.configuration;

import com.alibaba.csp.sentinel.datasource.Converter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;

/**
 * @author BiJi'an
 * @description
 * @date 2023-11-10 02:43
 */
//@Configuration
public class ElasticsearchConfiguration extends ElasticsearchConfigurationSupport {

  @Bean
  @Override
  public ElasticsearchCustomConversions elasticsearchCustomConversions() {
    List<Converter> converters= new ArrayList<>();
    converters.add(DateToLocalDateTimeConverter.INSTANCE);
    converters.add(StringToLocalDateTimeConverter.INSTANCE);
    converters.add(LongToLocalDateTimeConverter.INSTANCE);
    return new ElasticsearchCustomConversions(converters);
  }

  //保存类型为long类型
  @ReadingConverter
  enum LongToLocalDateTimeConverter implements Converter<Long, LocalDateTime> {

    INSTANCE;

    @Override
    public java.time.LocalDateTime convert(Long source) {
      return Instant.ofEpochMilli(source).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

  }
  //格式化后保存结果为String类型
  @ReadingConverter
  enum StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

    INSTANCE;

    @Override
    public java.time.LocalDateTime convert(String source) {
      DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      return LocalDateTime.parse(source,df);
    }

  }

  @WritingConverter
  enum DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {

    INSTANCE;

    @Override
    public LocalDateTime convert(Date date) {
      Instant instant = date.toInstant();
      return instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
  }
}