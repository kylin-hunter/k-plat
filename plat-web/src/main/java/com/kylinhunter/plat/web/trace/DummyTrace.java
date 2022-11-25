package com.kylinhunter.plat.web.trace;

import com.kylinhunter.plat.web.trace.explain.DummyTraceExplain;
import com.kylinhunter.plat.web.trace.explain.TraceExplain;

import io.github.kylinhunter.commons.strings.StringConst;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-11 02:14
 **/
public class DummyTrace implements Trace {
    private final DummyTraceExplain dummyTraceExplain = new DummyTraceExplain();

    @Override
    public String getId() {
        return StringConst.EMPTY;
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public String getToken() {
        return StringConst.EMPTY;
    }

    @Override
    public void setToken(String token) {

    }

    @Override
    public long getStartTime() {
        return 0;
    }

    @Override
    public long getEndTime() {
        return 0;
    }

    @Override
    public void setEndTime(long endTime) {

    }

    @Override
    public long getDurationTime() {
        return 0;
    }

    @Override
    public void setTraceExplain(TraceExplain traceExplain) {

    }

    @Override
    public TraceExplain getTraceExplain() {
        return dummyTraceExplain;
    }

    @Override
    public boolean isDummy() {
        return true;
    }
}
