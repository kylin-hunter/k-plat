package com.kylinhunter.plat.api.module.core.bean.vo;
import com.kylinhunter.plat.api.bean.vo.VO;
public interface  TenantUserVO  extends VO {
    String getDescription();
    void setDescription (String description);

    String getUserid();
    void setUserid (String userid);

    String getTenantid();
    void setTenantid (String tenantid);

    Integer getStatus();
    void setStatus (Integer status);



}