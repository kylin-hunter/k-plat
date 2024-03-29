package io.github.kylinhunter.plat.web.response;

import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.web.i18n.I18nUtils;
import io.github.kylinhunter.plat.web.request.RequestContext;
import io.github.kylinhunter.plat.web.trace.Trace;
import io.github.kylinhunter.plat.web.trace.TraceHandler;

import io.github.kylinhunter.commons.date.DateUtils;
import io.github.kylinhunter.commons.exception.ExceptionHelper;
import io.github.kylinhunter.commons.exception.common.KRuntimeException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2021/8/26
 **/
@Component
@Data
@Slf4j
public class ResponseService {
    private final TraceHandler traceHandler;
    private final RequestContext requestContext;

    /**
     * @param e
     * @return io.github.kylinhunter.plat.commons.web.response.DefaultResponse
     * @throws
     * @title toResponse
     * @description
     * @author BiJi'an
     * @date 2021/8/1 4:00 上午
     */
    public DefaultResponse toResponse(KRuntimeException e) {
        boolean debugMode = requestContext.isDebugMode();

        Trace trace = traceHandler.get();
        trace.setEndTime(System.currentTimeMillis());
        DefaultResponse response = new DefaultResponse();
        response.setCode(e.getErrInfo().getCode());
        String errMsg = I18nUtils.get(e.getErrInfo().getCode(), e.getExtra());
        if (errMsg != null) {
            response.setMsg(errMsg);
        } else {
            response.setMsg(ExceptionHelper.getMessage(e, debugMode, 1000));
        }
        response.setTime(trace.getStartTime());
        response.setDurationTime(trace.getDurationTime());
        response.setData(e.getExtra());
        if (debugMode) { // 更好地调试信息
            response.setStartTime(DateUtils.format(DateUtils.toLocalDateTime(trace.getStartTime())));
            response.setEndTime(DateUtils.format(DateUtils.toLocalDateTime(trace.getEndTime())));
        }
        response.setTraceId(trace.getId());
        if (!trace.getTraceExplain().isDummy()) {
            response.setTraceExplain(trace.getTraceExplain());
        }
        return response;

    }
}

