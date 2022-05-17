package com.kylinhunter.plat.web.trace;

import java.util.List;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-30 11:43
 **/

@Getter
@Setter
public class DefaulTrace implements Trace {
    private String id;
    private String agentId;
    private String currentAgentId;
    private String token;
    private String userId;
    private List<String> roleIds;
    private List<String> roleCodes;
    private boolean agentCreator = false;
    private String userName;
    private boolean superuser;
    private long startTime = System.currentTimeMillis();
    private long endTime = startTime;
    private long durationTime;
    private Explain explain;
    private boolean dummy = false;

    public DefaulTrace(String id, String agentId, String currentAgentId, String token) {
        if (id != null && id.length() > 0) {
            this.id = id;
        } else {
            this.id = UUID.randomUUID().toString();
        }
        this.agentId = agentId;
        this.currentAgentId = currentAgentId;
        this.token = token;
    }

    public DefaulTrace(String id, String token) {
        if (id != null && id.length() > 0) {
            this.id = id;
        } else {
            this.id = UUID.randomUUID().toString();
        }
        this.token = token;
    }

    @Override
    public void setEndTime(long endTime) {
        this.endTime = endTime;
        long cost = endTime - startTime;
        if (cost > 0) {
            this.durationTime = cost;
        }
    }

}
