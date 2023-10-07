package io.github.kylinhunter.plat.api.module.core.bean.vo;

import io.github.kylinhunter.plat.api.bean.vo.VO;

public interface  PermissionVO  extends VO {
    String getCode();
    void setCode (String code);

    String getName();
    void setName (String name);

    Integer getType();
    void setType (Integer type);

    String getDescription();
    void setDescription (String description);



}