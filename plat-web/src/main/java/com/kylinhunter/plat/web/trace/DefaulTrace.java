package com.kylinhunter.plat.web.trace;

import java.util.UUID;

import com.kylinhunter.plat.web.trace.explain.TraceExplain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-30 11:43
 **/

@Getter
@Setter
@NoArgsConstructor
public class DefaulTrace implements Trace {
    private String id;
    private String token;

    private long startTime = System.currentTimeMillis();
    private long endTime = startTime;
    private long durationTime;

    private TraceExplain traceExplain;

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

    @Override
    public boolean isDummy() {
        return false;
    }

}
