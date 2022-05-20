package com.kylinhunter.plat.web.trace.explain;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kylinhunter.plat.web.trace.CookieInfo;
import com.kylinhunter.plat.web.trace.Explain;

import lombok.Data;

/**
 * @description 无意义的explain作为默认explain使用
 * @author BiJi'an
 * @date   2022/01/01
 **/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DummyExplain implements Explain {
    private Map<String, Long> costs = Maps.newLinkedHashMap();
    private List<CookieInfo> cookieInfos = Lists.newArrayList();
    private Map<String, List<String>> headers = Maps.newLinkedHashMap();
    private Map<String, Object> others = Maps.newLinkedHashMap();
    private boolean dummy = true;
    @Override
    public Explain put(String key, Object value) {
        return this;
    }

    @Override
    public void timerStart(String key) {

    }

    @Override
    public void timerEnd(String key) {

    }

    @Override
    public void addCost(String key, long cost) {

    }

    @Override
    public long getCost(String key) {
        return 0;
    }

}
