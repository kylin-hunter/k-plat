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
import io.github.kylinhunter.commons.exception.explain.ExplainResult;
import java.sql.SQLIntegrityConstraintViolationException;
import org.apache.commons.lang3.StringUtils;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-08 00:01
 */
public class DaoErrExplainer extends AbstractExplainerSupplier {

  String SQLSTATE_CONSTRAINT = "23";

  @Override
  public void explain() {

    this.addExplainer(SQLIntegrityConstraintViolationException.class)
        .explain(
            e -> {
              ExplainResult explainResult = new ExplainResult(DaoErrInfos.CONSTRAINT, "数据约束异常");
              String message = e.getMessage();
              String sqlState = e.getSQLState();
              if (!StringUtils.isEmpty(sqlState)
                  && sqlState.startsWith(SQLSTATE_CONSTRAINT)
                  && !StringUtils.isEmpty(message)) {
                if (message.toLowerCase().indexOf("foreign") >= 0) {
                  explainResult =
                      new ExplainResult(DaoErrInfos.CONSTRAINT_FOREIGN, "数据约束异常,更新foreign异常");
                } else if (message.toLowerCase().indexOf("duplicate") >= 0) {
                  explainResult =
                      new ExplainResult(DaoErrInfos.CONSTRAINT_DUPLICATE, "数据约束异常,数据重复");
                }
              }
              explainResult.setExtra(e.getMessage());
              return explainResult;
            });
  }
}
