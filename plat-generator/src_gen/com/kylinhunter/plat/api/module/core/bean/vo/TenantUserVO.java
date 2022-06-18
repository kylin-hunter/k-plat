package com.kylinhunter.plat.api.module.core.bean.vo;
import com.kylinhunter.plat.api.bean.vo.VO;
public interface  TenantUserVO  extends VO {
    String getDescription();
    void setDescription (String description);

    Integer getType();
    void setType (Integer type);

    String getUserId();
    void setUserId (String userId);

    String getTenantId();
    void setTenantId (String tenantId);

    Integer getStatus();
    void setStatus (Integer status);



}