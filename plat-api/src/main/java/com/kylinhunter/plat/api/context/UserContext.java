package com.kylinhunter.plat.api.context;

import java.util.List;

public interface UserContext {

    String getTenantId();

    String getUserId();

    String getUserCode();

    String getUserName();

    boolean isAdmin();

    int getType();

    List<String> getRoleIds();

    List<String> getRoleCodes();

    void setTenantId(final String tenantId);

    void setUserId(final String userId);

    void setUserCode(final String userCode);

    void setUserName(final String userName);

    void setAdmin(final boolean admin);

    void setType(final int type);

    void setRoleIds(final List<String> roleIds);

    void setRoleCodes(final List<String> roleCodes);

    boolean isDummy();

    void setCheckTenant(boolean checkTenant);

    boolean isCheckTenant();
}
