package com.kylinhunter.plat.api.module.storage.bean.vo;
import com.kylinhunter.plat.api.bean.vo.VO;
public interface  FileMetadataVO  extends VO {
    String getExtension();
    void setExtension (String extension);

    String getDescription();
    void setDescription (String description);

    Integer getType();
    void setType (Integer type);

    String getPath();
    void setPath (String path);

    String getName();
    void setName (String name);

    String getRefId();
    void setRefId (String refId);

    String getContentType();
    void setContentType (String contentType);

    String getMd5();
    void setMd5 (String md5);



}