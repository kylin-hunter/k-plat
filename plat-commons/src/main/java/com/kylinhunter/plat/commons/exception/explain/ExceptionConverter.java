package com.kylinhunter.plat.commons.exception.explain;

import com.kylinhunter.plat.commons.exception.common.KRuntimeException;
import com.kylinhunter.plat.commons.exception.inner.InternalException;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2021/8/1
 **/
@Slf4j
public class ExceptionConverter {

    @Getter
    @Setter
    private ExceptionExplainer exceptionExplainer = new ExceptionExplainer();

    public <T extends Throwable> void register(Class<T> cls, ExceptionExplainFunc<T> exceptionExplainFunc) {
        exceptionExplainer.register(cls, exceptionExplainFunc);
    }

    public <R extends Throwable> KRuntimeException convert(R exception) {
        return convert(exception, true);
    }

    public <R extends Throwable> KRuntimeException convert(R exception, boolean trace) {
        if (KRuntimeException.class.isAssignableFrom(exception.getClass())) {
            return (KRuntimeException) exception;
        } else {
            try {
                ExceptionExplain exceptionExplain = exceptionExplainer.getExceptionExplain(exception);
                KRuntimeException runtimeException;
                if (trace) {
                    runtimeException = new KRuntimeException(exceptionExplain.getMsg(true), exception);
                } else {
                    runtimeException = new KRuntimeException(exceptionExplain.getMsg(true));
                }
                runtimeException.setErrInfo(exceptionExplain.getErrInfo());
                runtimeException.setExtra(exceptionExplain.getExtra());
                return runtimeException;
            } catch (Exception e) {
                throw new InternalException(e);
            }
        }

    }

}
