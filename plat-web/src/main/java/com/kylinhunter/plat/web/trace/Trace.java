package com.kylinhunter.plat.web.trace;

import java.util.List;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-30 11:43
 **/

public interface Trace {

    String getId();

    void setId(String id);

    String getAgentId();

    void setAgentId(String agentId);

    String getCurrentAgentId();

    void setCurrentAgentId(String currentAgentId);

    String getToken();

    void setToken(String token);

    String getUserId();

    void setUserId(String userId);

    String getUserName();

    void setUserName(String userName);

    long getStartTime();

    void setStartTime(long startTime);

    long getEndTime();

    void setEndTime(long endTime);

    long getDurationTime();

    void setDurationTime(long durationTime);

    void setExplain(Explain explain);

    boolean isDummy();

    Explain getExplain();

    boolean isSuperuser();

    void setSuperuser(boolean superuser);

    List<String> getRoleIds();

    void setRoleIds(List<String> roleIds);

    List<String> getRoleCodes();

    void setRoleCodes(List<String> roleCodes);

    boolean isAgentCreator();

    void setAgentCreator(boolean agentCreator);

}
