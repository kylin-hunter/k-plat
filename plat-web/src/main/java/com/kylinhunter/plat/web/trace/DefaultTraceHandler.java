
package com.kylinhunter.plat.web.trace;

import org.springframework.stereotype.Component;

import com.kylinhunter.plat.web.log.LogHelper;
import com.kylinhunter.plat.web.request.RequestContexService;
import com.kylinhunter.plat.web.trace.explain.DefaultExplain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description trace
 * @date 2021/7/29
 **/
@Component
@Slf4j
@RequiredArgsConstructor
public class DefaultTraceHandler implements TraceHandler {
    private final RequestContexService requestContexService;
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
     * @updateTime 2021/7/29 2:45 下午
     */
    private Trace tryCreateTraceFromRequest() {
        String traceId = requestContexService.getTraceId();
        String token = requestContexService.getToken();
        String agentId = requestContexService.getAgentId();
        Trace trace = new DefaulTrace(traceId, agentId, agentId, token);
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
        String traceId = requestContexService.getTraceId();
        String token = requestContexService.getSimpleToken();
        Trace trace = new DefaulTrace(traceId, token);
        trace.setExplain(this.tryCreateExplainFromRequest());
        return trace;

    }

    private Explain tryCreateExplainFromRequest() {
        if (requestContexService.isDebugMode()) {
            DefaultExplain defaultExplain = new DefaultExplain();
            defaultExplain.setHeaders(requestContexService.getHeaders());
            defaultExplain.setCookieInfos(requestContexService.getCookieInfos());
            return defaultExplain;
        }
        return DummyTrace.DUMMY_EXPLAIN;
    }

}
