package com.kylinhunter.plat.storage.service.local.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.api.module.storage.bean.entity.FileMetadata;
import com.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqCreate;
import com.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataResp;
import com.kylinhunter.plat.commons.exception.inner.KIOException;
import com.kylinhunter.plat.commons.exception.inner.ParamException;
import com.kylinhunter.plat.storage.config.StorageConfig;
import com.kylinhunter.plat.storage.exception.StorageException;
import com.kylinhunter.plat.storage.service.local.FileMetadataService;
import com.kylinhunter.plat.storage.service.local.StorageService;
import com.kylinhunter.plat.storage.service.local.helper.FileMetadataHelper;
import com.kylinhunter.plat.web.response.ResponseWriter;

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
    private StorageConfig storageConfig;
    @Autowired
    private ResponseWriter responseWriter;

    @Override
    public String upload(MultipartFile multipartFile) {
        FileMetadataReqCreate fileMetadataReqCreate = FileMetadataHelper.createFileMetadataReqCreate(multipartFile);
        fileMetadataReqCreate.setBucket(storageConfig.getS3().getBucket());
        final String id = this.checkExist(fileMetadataReqCreate);
        if (StringUtils.isEmpty(id)) {
            try (InputStream inputStream = multipartFile.getInputStream()) {
                this.upload(fileMetadataReqCreate, inputStream);
                final FileMetadataResp save = this.fileMetadataService.save(fileMetadataReqCreate);
                return save.getId();
            } catch (IOException e) {
                throw new KIOException("upload error ", e);
            }
        } else {
            return id;
        }

    }

    @Override
    public String upload(String path, File file) {
        FileMetadataReqCreate fileMetadataReqCreate = FileMetadataHelper.createFileMetadataReqCreate(file);
        fileMetadataReqCreate.setBucket(storageConfig.getS3().getBucket());
        final String id = this.checkExist(fileMetadataReqCreate);
        if (StringUtils.isEmpty(id)) {
            try (InputStream inputStream = new FileInputStream(file)) {
                this.upload(fileMetadataReqCreate, inputStream);
                final FileMetadataResp save = this.fileMetadataService.save(fileMetadataReqCreate);
                return save.getId();
            } catch (Exception e) {
                throw new StorageException("upload error", e);
            }
        } else {
            return id;
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

    private String checkExist(FileMetadataReqCreate fileMetadataReqCreate) {
        FileMetadata fileMetadata = this.fileMetadataService.findByMd5(fileMetadataReqCreate.getMd5());
        if (fileMetadata != null) {
            String name = fileMetadataReqCreate.getName();
            log.error("same file :" + name + "/" + fileMetadata.getName() + "/md5=" + fileMetadata.getMd5());
            return fileMetadata.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean exist(String id) {
        return this.fileMetadataService.getById(id) != null;
    }

    @Override
    public boolean delete(String id) {
        final FileMetadata fileMetadata = this.fileMetadataService.getById(id);
        if (fileMetadata != null) {
            this.delete(fileMetadata);
            this.fileMetadataService.delete(ReqDelete.of(id));
            return true;
        }
        return false;
    }

    public abstract boolean delete(FileMetadata fileMetadata);

}
