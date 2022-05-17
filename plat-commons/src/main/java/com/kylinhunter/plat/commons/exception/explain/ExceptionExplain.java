package com.kylinhunter.plat.commons.exception.explain;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-19 18:59
 **/
@FunctionalInterface
public interface ExceptionExplain<T extends Throwable> {
    ExplainResult explain(T t);
}
