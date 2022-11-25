package io.github.kylinhunter.plat.api.module.core.bean.vo;
import io.github.kylinhunter.plat.api.bean.vo.VO;
public interface  TenantVO  extends VO {
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