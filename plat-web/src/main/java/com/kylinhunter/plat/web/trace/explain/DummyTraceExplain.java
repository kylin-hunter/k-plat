package com.kylinhunter.plat.web.trace.explain;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kylinhunter.plat.web.trace.CookieInfo;

import lombok.Data;

/**
 * @author BiJi'an
 * @description 无意义的explain作为默认explain使用
 * @date 2022/01/01
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DummyTraceExplain implements TraceExplain {

    @Override
    public TraceExplain put(String key, Object value) {
        return this;
    }

    @Override
    public void recordTimeStart(String key) {

    }

    @Override
    public void recordTimeEnd(String key) {

    }

    @Override
    public void addTimeCost(String key, long cost) {

    }

    @Override
    public long getTimeCost(String key) {
        return 0;
    }

    @Override
    public Map<String, Long> getCosts() {
        return null;
    }

    @Override
    public List<CookieInfo> getCookieInfos() {
        return null;
    }

    @Override
    public Map<String, List<String>> getHeaders() {
        return null;
    }

    @Override
    public Map<String, Object> getOthers() {
        return null;
    }

    @Override
    public boolean isDummy() {
        return false;
    }
}
