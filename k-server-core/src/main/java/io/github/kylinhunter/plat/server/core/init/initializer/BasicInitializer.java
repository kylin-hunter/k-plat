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
package io.github.kylinhunter.plat.server.core.init.initializer;

import com.google.common.collect.Sets;
import io.github.kylinhunter.commons.exception.embed.InitException;
import java.util.Set;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-16 01:49
 */
public abstract class BasicInitializer implements Initializer, InitializingBean {
  @Getter private int order;

  private static final Set<Integer> orders = Sets.newHashSet();

  public int order() {
    String orderStr = this.getClass().getSimpleName().substring(5, 7);
    try {
      return Integer.parseInt(orderStr);
    } catch (NumberFormatException e) {
      throw new InitException("invalid order" + orderStr);
    }
  }

  public void afterPropertiesSet() {
    this.order = order();
    if (orders.contains(order)) {
      throw new InitException("invalid order" + order);
    }
    orders.add(order);
  }
}
