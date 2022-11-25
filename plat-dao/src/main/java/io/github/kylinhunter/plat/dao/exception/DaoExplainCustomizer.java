package io.github.kylinhunter.plat.dao.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;

import io.github.kylinhunter.commons.exception.explain.AbstractExplainerSupplier;
import io.github.kylinhunter.commons.exception.explain.Explainer;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-08 00:01
 **/
public class DaoExplainCustomizer extends AbstractExplainerSupplier {
    String SQLSTATE_CONSTRAINT = "23";
    @Override
    public void customize() {
        this.createExplain(DuplicateKeyException.class)
                .setExplainer(e -> new Explainer.ExplainResult(DaoErrInfoCustomizer.DUPLICATE));
        this.createExplain(SQLIntegrityConstraintViolationException.class)
                .setExplainer(e -> {

                    String message = e.getMessage();
                    String sqlState = e.getSQLState();
                    if (!StringUtils.isEmpty(sqlState) && sqlState.startsWith(SQLSTATE_CONSTRAINT) && !StringUtils
                            .isEmpty(message) && message.toLowerCase().indexOf("foreign") > 0) {
                        return new Explainer.ExplainResult(DaoErrInfoCustomizer.CONSTRAINT_FOREIGN,
                                "数据约束异常,更新foreign异常");
                    }
                    return new Explainer.ExplainResult(DaoErrInfoCustomizer.CONSTRAINT, "数据约束异常");
                });
    }
}
