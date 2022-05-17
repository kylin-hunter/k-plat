package com.kylinhunter.plat.commons.exception.explain;

import com.kylinhunter.plat.commons.exception.common.KRuntimeException;
import com.kylinhunter.plat.commons.exception.info.ErrInfos;

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

    /**
     * @param cls              cls
     * @param exceptionExplain exceptionExplain
     * @return void
     * @title register
     * @description
     * @author BiJi'an
     * @date 2022-05-18 00:30
     */
    public <T extends Throwable> void register(Class<T> cls, ExceptionExplain<T> exceptionExplain) {
        exceptionExplainer.register(cls, exceptionExplain);
    }

    /**
     * @param exception exception
     * @return KRuntimeException
     * @title convert
     * @description
     * @author BiJi'an
     * @date 2022-05-18 00:30
     */
    public KRuntimeException convert(Throwable exception) {
        return convert(exception, true);
    }

    /**
     * @param exception exception
     * @param trace     trace
     * @return KRuntimeException
     * @title convert
     * @description
     * @author BiJi'an
     * @date 2022-05-18 00:30
     */
    public KRuntimeException convert(Throwable exception, boolean trace) {
        if (KRuntimeException.class.isAssignableFrom(exception.getClass())) {
            return (KRuntimeException) exception;
        } else {
            try {
                ExplainResult explainResult = exceptionExplainer.explain(exception);
                if (trace) {
                    return new KRuntimeException(explainResult.getErrInfo(), explainResult.getExtra(),
                            explainResult.getMsg(), exception);
                } else {
                    return new KRuntimeException(explainResult.getErrInfo(), explainResult.getExtra(),
                            explainResult.getMsg());
                }
            } catch (Exception e) {
                log.error("convert error", e);
            }
            return new KRuntimeException(ErrInfos.UNKNOWN, exception.getMessage());
        }

    }

}
