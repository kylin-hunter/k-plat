package com.kylinhunter.plat.web.log;

import org.slf4j.MDC;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-30 21:05
 **/
public class LogHelper {
    private static final String MDC_TRACE_ID = "x-trace-id";
    private static final String MDC_USER_ID = "x-current-user";

    public static void setTraceId(String traceId) {
        MDC.put(MDC_TRACE_ID, traceId);
    }

    public static void setMdcUserId(String userId) {
        MDC.put(MDC_USER_ID, userId);
    }

    public static void clearContext() {
        MDC.clear();
    }
}
