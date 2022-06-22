package com.kylinhunter.plat.api.context;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-05-11 02:55
 **/
@Data
public class DummyUserContext implements UserContext, Serializable {

    @Override
    public String getTenantId() {
        return null;
    }

    @Override
    public String getUserId() {
        return null;
    }

    @Override
    public String getUserCode() {
        return null;
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public int getUserType() {
        return 0;
    }

    @Override
    public List<String> getRoleIds() {
        return null;
    }

    @Override
    public List<String> getRoleCodes() {
        return null;
    }

    @Override
    public void setTenantId(String tenantId) {

    }

    @Override
    public void setUserId(String userId) {

    }

    @Override
    public void setUserCode(String userCode) {

    }

    @Override
    public void setUserName(String userName) {

    }

    @Override
    public void setUserType(int type) {

    }

    @Override
    public void setRoleIds(List<String> roleIds) {

    }

    @Override
    public void setRoleCodes(List<String> roleCodes) {

    }

    @Override
    public boolean isDummy() {
        return true;
    }

    @Override
    public void setCheckTenant(boolean checkTenant) {

    }

    @Override
    public boolean isCheckTenant() {
        return true;
    }

    @Override
    public boolean isSuperAdmin() {
        return false;
    }
}
