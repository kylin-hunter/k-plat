package io.github.kylinhunter.plat.api.module.storage.bean.vo;
import io.github.kylinhunter.plat.api.bean.vo.VO;

public interface  FileMetadataVO  extends VO {
    String getExtension();
    void setExtension (String extension);

    String getDescription();
    void setDescription (String description);

    Integer getType();
    void setType (Integer type);

    String getBucket();
    void setBucket (String bucket);

    String getPath();
    void setPath (String path);

    Long getSize();
    void setSize (Long size);

    String getName();
    void setName (String name);

    String getContentType();
    void setContentType (String contentType);

    String getMd5();
    void setMd5 (String md5);



}