package com.kylinhunter.plat.storage.service.local.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import io.github.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import io.github.kylinhunter.plat.api.module.storage.bean.entity.FileMetadata;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqCreate;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataResp;
import com.kylinhunter.plat.storage.config.StorageConfig;
import com.kylinhunter.plat.storage.exception.StorageException;
import com.kylinhunter.plat.storage.service.local.FileMetadataService;
import com.kylinhunter.plat.storage.service.local.FileRelationService;
import com.kylinhunter.plat.storage.service.local.StorageService;
import com.kylinhunter.plat.storage.service.local.dto.UploadBeforeMsg;
import com.kylinhunter.plat.storage.service.local.helper.FileMetadataHelper;
import com.kylinhunter.plat.web.response.ResponseWriter;

import io.github.kylinhunter.commons.exception.embed.KIOException;
import io.github.kylinhunter.commons.exception.embed.ParamException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-30 02:17
 **/
@Slf4j
public abstract class AbstractStorageService implements StorageService {

    @Autowired
    private FileMetadataService fileMetadataService;
    @Autowired
    private FileRelationService fileRelationService;

    @Autowired
    private StorageConfig storageConfig;
    @Autowired
    private ResponseWriter responseWriter;

    public String uploadData(FileMetadataReqCreate fileMetadataReqCreate, InputStream inputStream) {
        UploadBeforeMsg uploadBeforeMsg = this.uploadBefore(fileMetadataReqCreate);
        if (uploadBeforeMsg.isNeedUpload()) {
            this.upload(fileMetadataReqCreate, inputStream);
        }
        FileMetadata oldFileMetadata = uploadBeforeMsg.getOldFileMetadata();
        if (oldFileMetadata != null) {
            return oldFileMetadata.getId();
        } else {
            FileMetadataResp save = this.fileMetadataService.save(fileMetadataReqCreate);
            return save.getId();
        }

    }

    @Override
    public String upload(MultipartFile multipartFile) {
        try (InputStream inputStream = multipartFile.getInputStream()) {
            FileMetadataReqCreate fileMetadataReqCreate = FileMetadataHelper.createFileMetadataReqCreate(multipartFile);
            return this.uploadData(fileMetadataReqCreate, inputStream);
        } catch (IOException e) {
            throw new KIOException("needUpload error ", e);
        }

    }

    @Override
    public String upload(File file) {
        try (InputStream inputStream = new FileInputStream(file)) {
            FileMetadataReqCreate fileMetadataReqCreate = FileMetadataHelper.createFileMetadataReqCreate(file);
            return this.uploadData(fileMetadataReqCreate, inputStream);
        } catch (IOException e) {
            throw new KIOException("needUpload error ", e);
        }

    }

    public void download(String id) {
        FileMetadata fileMetadata = fileMetadataService.getById(id);
        if (fileMetadata == null) {
            throw new ParamException("invalid id=" + id);
        }
        try (final InputStream inputStream = getDownloadInputStream(fileMetadata)) {
            responseWriter.writeFile(fileMetadata.getName(), inputStream, true);
        } catch (Exception e) {
            throw new StorageException("download error", e);
        }
    }

    public abstract InputStream getDownloadInputStream(FileMetadata fileMetadata);

    public abstract void upload(FileMetadataReqCreate fileMetadataReqCreate, InputStream inputStream);

    private UploadBeforeMsg uploadBefore(FileMetadataReqCreate fileMetadataReqCreate) {
        UploadBeforeMsg uploadBeforeMsg = new UploadBeforeMsg();
        fileMetadataReqCreate.setBucket(storageConfig.getS3().getBucket());
        final String md5 = fileMetadataReqCreate.getMd5();
        final String name = fileMetadataReqCreate.getName();
        FileMetadata sameMd5 = this.fileMetadataService.findByMd5(md5);
        if (sameMd5 != null) {
            uploadBeforeMsg.setNeedUpload(false);
            log.error("same file :" + name + "/" + sameMd5.getName() + "/md5=" + sameMd5.getMd5());
            FileMetadata sameMd5AndName = this.fileMetadataService.findByMd5AndName(md5, name);
            if (sameMd5AndName != null) {
                uploadBeforeMsg.setOldFileMetadata(sameMd5AndName);
            } else {
                fileMetadataReqCreate.setPath(sameMd5.getPath());

            }

        } else {
            uploadBeforeMsg.setNeedUpload(true);
        }

        return uploadBeforeMsg;
    }

    @Override
    public boolean exist(String id) {
        return this.fileMetadataService.getById(id) != null;
    }

    @Override
    public boolean delete(String id) {
        final FileMetadata fileMetadata = this.fileMetadataService.getById(id);
        if (fileMetadata != null) {
            if (this.fileRelationService.countByFileId(fileMetadata.getId()) > 0) {
                throw new ParamException("the file  was used,  can't  be delete");
            }
            this.fileMetadataService.delete(ReqDelete.of(id));
            if (this.fileMetadataService.findByMd5(fileMetadata.getMd5()) == null) {
                this.delete(fileMetadata);
            }
            return true;
        }
        return false;
    }

    public abstract boolean delete(FileMetadata fileMetadata);

}
