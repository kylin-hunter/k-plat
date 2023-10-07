package io.github.kylinhunter.plat.api.module.core.bean.vo;
import io.github.kylinhunter.plat.api.bean.vo.VO;
public interface  UserRoleVO  extends VO {
    String getUserId();
    void setUserId (String userId);

    String getRoleId();
    void setRoleId (String roleId);



}