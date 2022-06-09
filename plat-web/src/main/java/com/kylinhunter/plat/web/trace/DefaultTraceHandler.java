
package com.kylinhunter.plat.web.trace;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.web.log.LogHelper;
import com.kylinhunter.plat.web.request.RequestContext;
import com.kylinhunter.plat.web.trace.explain.DefaultExplain;

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
    private static final Trace DUMMY_TRACE = new DummyTrace();

    private ThreadLocal<Trace> traces = InheritableThreadLocal.withInitial(() -> DUMMY_TRACE);

    @Override
    public Trace create() {
        Trace trace = this.tryCreateTraceFromRequest();
        traces.set(trace);
        LogHelper.setContext(trace);
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
     * @throws
     * @title 从request获取Trace信息
     * @description
     * @author BiJi'an
     * @date 2022/01/01 2:45 下午
     */
    private Trace tryCreateTraceFromRequest() {
        String traceId = requestContext.getTraceId();
        String token = requestContext.getToken();
        String tenantId = requestContext.getTenantId();
        Trace trace = new DefaulTrace(traceId, tenantId, tenantId, token);
        trace.setExplain(this.tryCreateExplainFromRequest());
        return trace;

    }

    /**
     * @return com.kylinhunter.plat.commons.trace.Trace
     * @throws
     * @title 从header获取Trace信息
     * @description
     */
    private Trace tryCreateTraceFromHeader() {
        String traceId = requestContext.getTraceId();
        String token = requestContext.getSimpleToken();
        Trace trace = new DefaulTrace(traceId, token);
        trace.setExplain(this.tryCreateExplainFromRequest());
        return trace;

    }

    private Explain tryCreateExplainFromRequest() {
        if (requestContext.isDebugMode()) {
            DefaultExplain defaultExplain = new DefaultExplain();
            defaultExplain.setHeaders(requestContext.getHeaders());
            defaultExplain.setCookieInfos(requestContext.getCookieInfos());
            return defaultExplain;
        }
        return DummyTrace.DUMMY_EXPLAIN;
    }

}
