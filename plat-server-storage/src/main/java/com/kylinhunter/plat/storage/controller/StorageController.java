package com.kylinhunter.plat.storage.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kylinhunter.plat.storage.service.local.StorageService;
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

    private final StorageService storageService;

    @ApiOperation(value = "upload", notes = "上传")
    @PostMapping(value = "/upload")
    String upload(@RequestParam(value = "file") final MultipartFile multipartFile) throws Exception {
        return storageService.upload(multipartFile);

    }

    @ApiOperation(value = "download", notes = "下载")
    @GetMapping(value = "/download")
    void download(@RequestParam("id") String id) {
        storageService.download(id);
    }

    @ApiOperation("文件是否存在")
    @GetMapping(value = "/exists")
    public Boolean exists(String id) {
        return storageService.exist(id);
    }

    @ApiOperation("文件删除")
    @DeleteMapping(value = "/delete")
    public Boolean delete(@RequestParam("id") String id) {
        return storageService.delete(id);
    }
}
