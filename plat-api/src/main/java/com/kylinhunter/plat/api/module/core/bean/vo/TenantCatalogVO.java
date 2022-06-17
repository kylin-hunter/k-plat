package com.kylinhunter.plat.api.module.core.bean.vo;
import com.kylinhunter.plat.api.bean.vo.VO;
public interface  TenantCatalogVO  extends VO {
    String getCode();
    void setCode (String code);

    Integer getLevel();
    void setLevel (Integer level);

    String getDescription();
    void setDescription (String description);

    Integer getType();
    void setType (Integer type);

    String getParentId();
    void setParentId (String parentId);

    String getPath();
    void setPath (String path);

    String getName();
    void setName (String name);

    Integer getStatus();
    void setStatus (Integer status);



}