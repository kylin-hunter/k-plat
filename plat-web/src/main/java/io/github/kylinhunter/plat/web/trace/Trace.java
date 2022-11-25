package io.github.kylinhunter.plat.web.trace;

import io.github.kylinhunter.plat.web.trace.explain.TraceExplain;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-30 11:43
 **/

public interface Trace {

    String getId();

    void setId(String id);

    String getToken();

    void setToken(String token);

    long getStartTime();

    long getEndTime();

    void setEndTime(long endTime);

    long getDurationTime();

    void setTraceExplain(TraceExplain traceExplain);

    TraceExplain getTraceExplain();

    boolean isDummy();
}
