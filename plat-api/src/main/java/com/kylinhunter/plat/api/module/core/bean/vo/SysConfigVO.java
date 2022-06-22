package com.kylinhunter.plat.api.module.core.bean.vo;
import com.kylinhunter.plat.api.bean.vo.VO;
public interface  SysConfigVO  extends VO {
    String getCode();
    void setCode (String code);

    String getDescription();
    void setDescription (String description);

    Integer getType();
    void setType (Integer type);

    String getValue();
    void setValue (String value);

    Integer getStatus();
    void setStatus (Integer status);



}