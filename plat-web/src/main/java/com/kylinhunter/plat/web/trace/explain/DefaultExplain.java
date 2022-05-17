package com.kylinhunter.plat.web.trace.explain;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kylinhunter.plat.web.trace.CookieInfo;
import com.kylinhunter.plat.web.trace.Explain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * @description
 * @author BiJi'an
 * @date   2021/7/29
 **/

@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
@Slf4j
public class DefaultExplain implements Explain {
    private Map<String, Long> costs = Maps.newLinkedHashMap();
    private List<CookieInfo> cookieInfos = Lists.newArrayList();
    private Map<String, List<String>> headers = Maps.newLinkedHashMap();
    private Map<String, Object> others = Maps.newLinkedHashMap();
    private boolean dummy = false;

    @Override
    public Explain put(String key, Object value) {
        others.put(key, value);
        return this;
    }

    @Override
    public void timerStart(String key) {
        costs.put(key, System.currentTimeMillis());
    }

    @Override
    public void timerEnd(String key) {
        Long startTime = costs.get(key);
        if (startTime != null && startTime > 0) {
            costs.put(key, System.currentTimeMillis() - startTime);
        } else {
            log.warn("invalid startTime {},{}:", key, startTime);
            costs.put(key, 0L);

        }
    }

    @Override
    public void addCost(String key, long cost) {
        costs.put(key, cost);
    }

    @Override
    public long getCost(String key) {
        Long cost = costs.get(key);
        if (cost != null && cost > 0) {
            return cost;
        } else {
            return 0;
        }
    }

}
