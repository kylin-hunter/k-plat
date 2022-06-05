package com.kylinhunter.plat.api.module.core.bean.vo;

public interface UserVO {
    String getUserId();

    String getUserName();

    String getPassword();

    String getSource();

    Integer getType();

    Integer getStatus();

    String getDescription();

    void setUserId(final String userId);

    void setUserName(final String userName);

    void setPassword(final String password);

    void setSource(final String source);

    void setType(final Integer type);

    void setStatus(final Integer status);

    void setDescription(final String description);
}
