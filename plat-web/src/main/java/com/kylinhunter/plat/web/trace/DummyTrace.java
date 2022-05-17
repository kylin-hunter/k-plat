package com.kylinhunter.plat.web.trace;

import java.util.Collections;
import java.util.List;

import com.kylinhunter.plat.web.trace.explain.DummyExplain;

import lombok.Data;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-30 11:43
 **/
@Data
public class DummyTrace implements Trace {
    public static final DummyExplain DUMMY_EXPLAIN = new DummyExplain();
    private boolean dummy = true;

    @Override
    public String getId() {
        return "";
    }

    @Override
    public void setId(String id) {

    }

    @Override
    public String getAgentId() {
        return "";
    }

    @Override
    public void setAgentId(String agentId) {

    }

    @Override
    public String getCurrentAgentId() {
        return "";
    }

    @Override
    public void setCurrentAgentId(String currentAgentId) {

    }

    @Override
    public String getToken() {
        return null;
    }

    @Override
    public void setToken(String token) {

    }

    @Override
    public String getUserId() {
        return "";
    }

    @Override
    public void setUserId(String userId) {

    }

    @Override
    public String getUserName() {
        return "";
    }

    @Override
    public void setUserName(String userName) {

    }

    @Override
    public long getStartTime() {
        return 0;
    }

    @Override
    public void setStartTime(long startTime) {

    }

    @Override
    public long getEndTime() {
        return 0;
    }

    @Override
    public void setEndTime(long endTime) {

    }

    @Override
    public long getDurationTime() {
        return 0;
    }

    @Override
    public void setDurationTime(long durationTime) {

    }

    @Override
    public void setExplain(Explain explain) {

    }

    @Override
    public Explain getExplain() {
        return DUMMY_EXPLAIN;
    }

    @Override
    public boolean isSuperuser() {
        return false;
    }

    @Override
    public void setSuperuser(boolean superuser) {

    }

    @Override
    public List<String> getRoleIds() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public void setRoleIds(List<String> roleIds) {

    }

    @Override
    public List<String> getRoleCodes() {
        return Collections.EMPTY_LIST;

    }

    @Override
    public void setRoleCodes(List<String> roleCodes) {

    }

    @Override
    public boolean isAgentCreator() {
        return false;
    }

    @Override
    public void setAgentCreator(boolean agentCreator) {

    }
}
