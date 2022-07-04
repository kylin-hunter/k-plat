package com.kylinhunter.plat.api.module.storage.bean.vo;
import com.kylinhunter.plat.api.bean.vo.VO;
public interface  FileRelationVO  extends VO {
    Integer getType();
    void setType (Integer type);

    String getMasterId();
    void setMasterId (String masterId);

    String getFileId();
    void setFileId (String fileId);



}