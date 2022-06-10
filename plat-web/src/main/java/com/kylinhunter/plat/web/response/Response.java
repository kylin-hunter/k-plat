package com.kylinhunter.plat.web.response;

import java.io.Serializable;

import com.kylinhunter.plat.web.trace.explain.TraceExplain;

/**
 * @description 返回响应
 * @author BiJi'an
 * @date   2022/01/01
 **/

public interface Response<T> extends Serializable {
    int getCode();

    void setCode(int code);

    String getMsg();

    void setMsg(String msg);

    T getData();

    void setData(T data);

    long getTime();

    void setTime(long time);

    String getStartTime();

    void setStartTime(String startTime);

    String getEndTime();

    void setEndTime(String endTime);

    long getDurationTime();

    void setDurationTime(long durationTime);

    String getTraceId();

    void setTraceId(String traceId);

    TraceExplain getTraceExplain();

    void setTraceExplain(TraceExplain traceExplain);

}
