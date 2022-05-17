package com.kylinhunter.plat.commons.exception.explain;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.kylinhunter.plat.commons.exception.common.KThrowable;
import com.kylinhunter.plat.commons.exception.explain.ExceptionFinder.ExceptionFind;
import com.kylinhunter.plat.commons.exception.info.ErrInfos;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2021/8/1
 **/
@Slf4j
public class ExceptionExplainer {

    private final Map<Class<? extends Throwable>, ExceptionExplain<Throwable>> exceptionExplains =
            Maps.newHashMap();
    public Set<Class<? extends Throwable>> allExceptions = Sets.newHashSet();

    /**
     * @param cls              cls
     * @param exceptionExplain exceptionExplain
     * @title register
     * @description
     * @author BiJi'an
     * @date 2022-01-01 01:25
     */

    @SuppressWarnings("unchecked")
    public <T extends Throwable> void register(Class<T> cls, ExceptionExplain<T> exceptionExplain) {
        exceptionExplains.put(cls, (ExceptionExplain<Throwable>) exceptionExplain);
        allExceptions.add(cls);
    }

    /**
     * @param throwable throwable
     * @return com.kylinhunter.plat.commons.exception.explain.ExplainResult
     * @title explain explain
     * @description
     * @author BiJi'an
     * @date 2022-05-18 00:31
     */

    public ExplainResult explain(Throwable throwable) {
        ExplainResult explainResult = null;
        if (throwable instanceof KThrowable) {
            explainResult = new ExplainResult((KThrowable) throwable, throwable.getMessage());
        } else {
            ExceptionFind exceptionFind = ExceptionFinder.find(throwable, true, allExceptions);
            if (exceptionFind != null) {
                ExceptionExplain<Throwable> exceptionExplain = exceptionExplains.get(exceptionFind.getSource());
                if (exceptionExplain != null) {
                    explainResult = exceptionExplain.explain(exceptionFind.getTarget());
                }
            }
            if (explainResult == null) {
                explainResult = new ExplainResult(ErrInfos.UNKNOWN, throwable.getMessage());
            }
        }
        return explainResult;
    }
}
