package com.kylinhunter.plat.commons.exception.explain;

import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.kylinhunter.plat.commons.exception.common.ThrowableEx;
import com.kylinhunter.plat.commons.exception.info.ErrInfos;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2021/8/1
 **/
@Slf4j
public class ExceptionExplainer {

    private final Map<Class<? extends Throwable>, ExceptionExplainFunc<Throwable>> exceptionExplainers =
            Maps.newHashMap();
    public Set<Class<? extends Throwable>> allExceptions = Sets.newHashSet();

    /**
     * @param cls                cls
     * @param exceptionExplainFunc exceptionExplainFunc
     * @title register
     * @description
     * @author BiJi'an
     * @date 2022-01-01 01:25
     */
    @SuppressWarnings("unchecked")
    public <T extends Throwable> void register(Class<T> cls, ExceptionExplainFunc<T> exceptionExplainFunc) {
        exceptionExplainers.put(cls, (ExceptionExplainFunc<Throwable>) exceptionExplainFunc);
        allExceptions.add(cls);
    }

    public ExceptionExplain getExceptionExplain(Throwable throwable) {
        ExceptionExplain exceptionExplain = null;
        if (throwable instanceof ThrowableEx) {
            exceptionExplain = new ExceptionExplain((ThrowableEx) throwable, throwable.getMessage());
        } else {
            ExceptionFind exceptionFind = ExceptionFinder.find(throwable, true, allExceptions);
            if (exceptionFind != null) {
                ExceptionExplainFunc<Throwable> exceptionExplainFunc = exceptionExplainers.get(exceptionFind.getSource());
                if (exceptionExplainFunc != null) {
                    exceptionExplain = exceptionExplainFunc.explain(exceptionFind.getTarget());
                }
            }
            if (exceptionExplain == null) {
                exceptionExplain = new ExceptionExplain(ErrInfos.UNKNOWN, throwable.getMessage());
            }
        }
        return exceptionExplain;
    }
}
