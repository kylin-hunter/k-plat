package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.VO;

public interface  RolePermissionVO  extends VO {
    String getRoleId();
    void setRoleId (String roleId);

    String getPermissionId();
    void setPermissionId (String permissionId);



}