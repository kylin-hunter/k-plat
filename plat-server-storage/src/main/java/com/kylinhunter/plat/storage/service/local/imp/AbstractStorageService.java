package com.kylinhunter.plat.storage.service.local.imp;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;

import com.kylinhunter.plat.storage.service.local.FileMetadataService;
import com.kylinhunter.plat.storage.service.local.StorageService;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-30 02:17
 **/
public abstract class AbstractStorageService implements StorageService {

    @Autowired
    private FileMetadataService fileMetadataService;

    public void sd(File file){
    }

}
