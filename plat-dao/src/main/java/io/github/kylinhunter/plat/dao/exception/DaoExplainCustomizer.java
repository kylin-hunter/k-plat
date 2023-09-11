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
package io.github.kylinhunter.plat.dao.exception;

import io.github.kylinhunter.commons.exception.explain.AbstractExplainerSupplier;
import io.github.kylinhunter.commons.exception.explain.Explainer;
import java.sql.SQLIntegrityConstraintViolationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-08 00:01
 */
public class DaoExplainCustomizer extends AbstractExplainerSupplier {
  String SQLSTATE_CONSTRAINT = "23";

  @Override
  public void customize() {
    this.createExplain(DuplicateKeyException.class)
        .setExplainer(e -> new Explainer.ExplainResult(DaoErrInfoCustomizer.DUPLICATE));
    this.createExplain(SQLIntegrityConstraintViolationException.class)
        .setExplainer(
            e -> {
              String message = e.getMessage();
              String sqlState = e.getSQLState();
              if (!StringUtils.isEmpty(sqlState)
                  && sqlState.startsWith(SQLSTATE_CONSTRAINT)
                  && !StringUtils.isEmpty(message)
                  && message.toLowerCase().indexOf("foreign") > 0) {
                return new Explainer.ExplainResult(
                    DaoErrInfoCustomizer.CONSTRAINT_FOREIGN, "数据约束异常,更新foreign异常");
              }
              return new Explainer.ExplainResult(DaoErrInfoCustomizer.CONSTRAINT, "数据约束异常");
            });
  }
}
