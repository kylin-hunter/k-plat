
package io.github.kylinhunter.plat.web.trace;

import org.springframework.stereotype.Component;

import io.github.kylinhunter.plat.web.log.LogHelper;
import io.github.kylinhunter.plat.web.request.RequestContext;
import io.github.kylinhunter.plat.web.trace.explain.DefaultTraceExplain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description trace
 * @date 2022/01/01
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class DefaultTraceHandler implements TraceHandler {
    private final RequestContext requestContext;
    private static final DummyTrace DUMMY_TRACE = new DummyTrace();
    private final ThreadLocal<Trace> traces = InheritableThreadLocal.withInitial(() -> DUMMY_TRACE);

    @Override
    public Trace create() {
        Trace trace = this.tryCreateTraceFromRequest();
        traces.set(trace);
        LogHelper.setTraceId(trace.getId());
        return trace;
    }

    @Override
    public Trace get() {
        return traces.get();
    }

    @Override
    public void remove() {
        LogHelper.clearContext();
        traces.set(DUMMY_TRACE);
    }

    /**
     * @return com.kylinhunter.plat.commons.trace.Trace
     * @title 从request获取Trace信息
     * @description
     * @author BiJi'an
     * @date 2022/01/01 2:45 下午
     */
    private Trace tryCreateTraceFromRequest() {
        String traceId = requestContext.getTraceId();
        String token = requestContext.getToken();
        Trace trace = new DefaulTrace(traceId, token);

        if (requestContext.isDebugMode()) {
            DefaultTraceExplain defaultExplain = new DefaultTraceExplain();
            defaultExplain.setHeaders(requestContext.getHeaders());
            defaultExplain.setCookieInfos(requestContext.getCookieInfos());
            trace.setTraceExplain(defaultExplain);

        } else {
            trace.setTraceExplain(DUMMY_TRACE.getTraceExplain());
        }
        return trace;

    }

}
