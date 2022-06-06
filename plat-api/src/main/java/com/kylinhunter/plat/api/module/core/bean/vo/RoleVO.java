package com.kylinhunter.plat.api.module.core.bean.vo;
import com.kylinhunter.plat.api.bean.vo.VO;
public interface  RoleVO  extends VO {
    String getRoleId();
    void setRoleId (String roleId);

    String getDescription();
    void setDescription (String description);

    Integer getType();
    void setType (Integer type);

    String getRoleName();
    void setRoleName (String roleName);

    Integer getStatus();
    void setStatus (Integer status);



}