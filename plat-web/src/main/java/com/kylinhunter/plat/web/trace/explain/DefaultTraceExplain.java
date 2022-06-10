package com.kylinhunter.plat.web.trace.explain;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Maps;
import com.kylinhunter.plat.web.trace.CookieInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022/01/01
 **/

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Slf4j
public class DefaultTraceExplain implements TraceExplain {
    private Map<String, List<String>> headers;
    private List<CookieInfo> cookieInfos;

    private Map<String, Long> costs = Maps.newLinkedHashMap();
    private Map<String, Object> others = Maps.newLinkedHashMap();

    @Override
    public TraceExplain put(String key, Object value) {
        others.put(key, value);
        return this;
    }

    @Override
    public void recordTimeStart(String key) {
        costs.put(key, System.currentTimeMillis());
    }

    @Override
    public void recordTimeEnd(String key) {
        Long startTime = costs.get(key);
        if (startTime != null && startTime > 0) {
            costs.put(key, System.currentTimeMillis() - startTime);
        } else {
            log.warn("invalid startTime {},{}:", key, startTime);
            costs.put(key, 0L);

        }
    }

    @Override
    public void addTimeCost(String key, long cost) {
        costs.put(key, cost);
    }

    @Override
    public long getTimeCost(String key) {
        Long cost = costs.get(key);
        if (cost != null && cost > 0) {
            return cost;
        } else {
            return 0;
        }
    }

    @Override
    public boolean isDummy() {
        return false;
    }

}
