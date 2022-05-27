package com.kylinhunter.plat.web.log;

import org.slf4j.MDC;

import com.kylinhunter.plat.web.trace.Trace;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-30 21:05
 **/
public class LogHelper {
    private static final String MDC_TRACE_ID = "x-trace-id";
    private static final String MDC_USER_ID = "x-current-user";
    private static final String MDC_USER_NAME = "x-current-user-name1";
    private static final String MDC_AGENT_ID = "x-agent-id";

    /**
     * @param trace
     * @return void
     * @throws
     * @title addToMDC
     * @description
     * @author BiJi'an
     * @date 2022/01/01 2:53 下午
     */

    public static void setContext(Trace trace) {
        MDC.put(MDC_TRACE_ID, trace.getId());
        MDC.put(MDC_AGENT_ID, trace.getAgentId());
        MDC.put(MDC_USER_ID, trace.getUserId());
        MDC.put(MDC_USER_NAME, trace.getUserName());
    }

    public static void clearContext() {
        MDC.clear();
    }
}
