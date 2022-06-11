package com.kylinhunter.plat.api.module.core.bean.vo;
import com.kylinhunter.plat.api.bean.vo.VO;
public interface  RoleVO  extends VO {
    String getCode();
    void setCode (String code);

    String getDescription();
    void setDescription (String description);

    Integer getType();
    void setType (Integer type);

    String getName();
    void setName (String name);

    Integer getStatus();
    void setStatus (Integer status);



}