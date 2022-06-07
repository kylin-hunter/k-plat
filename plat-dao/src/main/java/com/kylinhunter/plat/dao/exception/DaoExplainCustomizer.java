package com.kylinhunter.plat.dao.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;

import com.kylinhunter.plat.commons.exception.explain.ExplainCustomizer;
import com.kylinhunter.plat.commons.exception.explain.ExceptionExplainer;
import com.kylinhunter.plat.commons.exception.explain.ExplainInfo;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-08 00:01
 **/
public class DaoExplainCustomizer implements ExplainCustomizer {
    String SQLSTATE_CONSTRAINT = "23";

    @Override
    public void customize(ExceptionExplainer exceptionExplainer) {

        exceptionExplainer.register(DuplicateKeyException.class, e ->
                new ExplainInfo(DaoErrInfoCustomizer.DUPLICATE, "数据约束异常:数据重复"));

        exceptionExplainer.register(SQLIntegrityConstraintViolationException.class, (e) -> {
            String message = e.getMessage();
            String sqlState = e.getSQLState();
            if (!StringUtils.isEmpty(sqlState) && sqlState.startsWith(SQLSTATE_CONSTRAINT) && !StringUtils
                    .isEmpty(message) && message.toLowerCase().indexOf("foreign") > 0) {
                return new ExplainInfo(DaoErrInfoCustomizer.CONSTRAINT_FOREIGN, "数据约束异常,更新foreign异常");
            }
            return new ExplainInfo(DaoErrInfoCustomizer.CONSTRAINT, "数据约束异常");
        });

    }
}
