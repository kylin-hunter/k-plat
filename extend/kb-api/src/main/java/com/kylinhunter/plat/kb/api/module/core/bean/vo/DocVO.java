package com.kylinhunter.plat.kb.api.module.core.bean.vo;
import com.kylinhunter.plat.api.bean.vo.VO;
import java.math.BigDecimal;

public interface  DocVO  extends VO {
    String getSummary();
    void setSummary (String summary);

    String getKeywords();
    void setKeywords (String keywords);

    String getDescription();
    void setDescription (String description);

    String getTitle();
    void setTitle (String title);

    Integer getType();
    void setType (Integer type);

    BigDecimal getVersion();
    void setVersion (BigDecimal version);

    String getContent();
    void setContent (String content);

    String getTags();
    void setTags (String tags);

    String getCatId();
    void setCatId (String catId);

    String getSecondaryCatId();
    void setSecondaryCatId (String secondaryCatId);

    Integer getStatus();
    void setStatus (Integer status);

    String getMd5();
    void setMd5 (String md5);



}