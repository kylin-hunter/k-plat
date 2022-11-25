package com.kylinhunter.plat.web.response;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.kylinhunter.plat.web.request.RequestContext;
import com.kylinhunter.plat.web.trace.Trace;
import com.kylinhunter.plat.web.trace.TraceHandler;

import io.github.kylinhunter.commons.date.DateUtils;
import io.github.kylinhunter.commons.json.JsonOptions;
import io.github.kylinhunter.commons.json.JsonUtils;
import io.github.kylinhunter.commons.sys.KConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    private final TraceHandler traceHandler;
    private final RequestContext requestContext;

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        Class<?> dc = methodParameter.getDeclaringClass();
        if (dc.getPackage().getName().startsWith(KConst.K_BASE_PACKAGE)) {
            if (dc.getAnnotation(RestController.class) != null || dc.getAnnotation(ResponseBody.class) != null
                    || methodParameter.hasMethodAnnotation(ResponseBody.class)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object returnValue, MethodParameter methodParameter, MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        if (returnValue instanceof CustomResponse) {
            return returnValue;
        }
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Trace trace = traceHandler.get();
        trace.setEndTime(System.currentTimeMillis());

        boolean isResponse = returnValue instanceof Response;
        Response<Object> response = isResponse ? (Response<Object>) returnValue : new DefaultResponse();
        response.setTime(trace.getStartTime());
        response.setDurationTime(trace.getDurationTime());
        response.setTraceId(trace.getId());

        if (requestContext.isDebugMode()) { // 更好地调试信息
            response.setStartTime(DateUtils.format(DateUtils.toLocalDateTime(trace.getStartTime())));
            response.setEndTime(DateUtils.format(DateUtils.toLocalDateTime(trace.getEndTime())));
        }
        if (!trace.getTraceExplain().isDummy()) {
            response.setTraceExplain(trace.getTraceExplain());
        }
        log.info(
                req.getRequestURI() + "'s response:" + JsonUtils.writeToString(response, JsonOptions.NO_FAIL));

        if (!isResponse) {

            response.setData(returnValue);
            if (returnValue instanceof String) {
                return JsonUtils.writeToString(response, JsonOptions.NO_FAIL);
            }
        }
        return response;

    }
}