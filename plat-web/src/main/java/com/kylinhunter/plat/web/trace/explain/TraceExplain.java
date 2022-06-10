package com.kylinhunter.plat.web.trace.explain;

import java.util.List;
import java.util.Map;

import com.kylinhunter.plat.web.trace.CookieInfo;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-30 11:07
 **/
public interface TraceExplain {

    TraceExplain put(String key, Object value);

    void recordTimeStart(String key);

    void recordTimeEnd(String key);

    void addTimeCost(String key, long cost);

    long getTimeCost(String key);

    Map<String, Long> getCosts();

    List<CookieInfo> getCookieInfos();

    Map<String, List<String>> getHeaders();

    Map<String, Object> getOthers();

    boolean isDummy();
}
