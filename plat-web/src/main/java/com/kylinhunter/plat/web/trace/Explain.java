package com.kylinhunter.plat.web.trace;

import java.util.List;
import java.util.Map;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-30 11:07
 **/
public interface Explain {

    Explain put(String key, Object value);

    void timerStart(String key);

    void timerEnd(String key);

    void addCost(String key, long cost);

    long getCost(String key);

    Map<String, Long> getCosts();

    List<CookieInfo> getCookieInfos();

    Map<String, List<String>> getHeaders();

    Map<String, Object> getOthers();

    boolean isDummy();
}
