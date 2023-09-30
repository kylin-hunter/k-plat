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
package io.github.kylinhunter.plat.dao.configuration;

import io.github.kylinhunter.plat.dao.service.local.component.FilterComponent;
import io.github.kylinhunter.plat.dao.service.local.component.SortComponent;
import io.github.kylinhunter.plat.dao.service.local.ex.FilterCustoms;
import io.github.kylinhunter.plat.dao.service.local.interceptor.DeleteInterceptor;
import io.github.kylinhunter.plat.dao.service.local.interceptor.QueryAccurateInterceptor;
import io.github.kylinhunter.plat.dao.service.local.interceptor.QueryComplexInterceptor;
import io.github.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-07 00:06
 */
@Configuration
public class DaoComonConfiguration {

  @Bean
  public FilterCustoms filterCustoms(){
    return new FilterCustoms();
  }

  @Bean
  public FilterComponent filterComponent(FilterCustoms filterCustoms) {
    return new FilterComponent(filterCustoms);
  }
  @Bean
  public SortComponent sortComponent( ) {
    return new SortComponent();
  }

  @Bean
  @Primary
  public QueryComplexInterceptor queryComplexInterceptor(SortComponent sortComponent ,FilterComponent filterComponent){
    return new QueryComplexInterceptor(sortComponent,filterComponent);
  }

  @Bean
  @Primary
  public DeleteInterceptor deleteInterceptor(){
    return new DeleteInterceptor();
  }

  @Bean
  @Primary
  public SaveOrUpdateInterceptor saveOrUpdateInterceptor(){
    return new SaveOrUpdateInterceptor();
  }

  @Bean
  @Primary
  public QueryAccurateInterceptor queryAccurateInterceptor(){
    return new QueryAccurateInterceptor();
  }

}
