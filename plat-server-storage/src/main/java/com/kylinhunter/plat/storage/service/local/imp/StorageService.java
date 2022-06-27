package com.kylinhunter.plat.storage.service.local.imp;

import java.io.File;

public interface StorageService {
    public void upload(String bucket, String path, String fileName, File file);

}
