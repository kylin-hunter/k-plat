package com.kylinhunter.plat.storage.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kylinhunter.plat.storage.minio.service.MinIOService;
import com.kylinhunter.plat.storage.service.local.imp.StorageService;
import com.kylinhunter.plat.web.controller.CommonController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-27 01:45
 **/
@RestController
@RequestMapping("/api/v1/storage")
@Api(value = "Storage相关接口")
@RequiredArgsConstructor
public class StorageController extends CommonController {

    private StorageService storageService;

    @ApiOperation(value = "minio", notes = "使用原文件名，相同文件名会覆盖")
    @PostMapping(value = "/minio")
    Boolean uploadFile(MultipartFile file) throws Exception {
        file.getInputStream();
        storageService.upload("bijian", "/fat1/", file.getOriginalFilename(), null);
        return false;
    }
}
