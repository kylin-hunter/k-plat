package com.kylinhunter.plat.storage.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kylinhunter.plat.api.bean.vo.delete.ReqDelete;
import com.kylinhunter.plat.storage.service.local.StorageService;
import com.kylinhunter.plat.web.controller.CommonController;
import com.kylinhunter.plat.web.response.DefaultResponse;

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
    Boolean upload(@RequestParam(value = "file") final MultipartFile multipartFile) throws Exception {
        storageService.upload(multipartFile);
        return true;
    }

    @ApiOperation(value = "downlaod", notes = "下载")
    @PostMapping(value = "/downlaod")
    Boolean downlaod(MultipartFile file) throws Exception {
        file.getInputStream();
        //        storageService.upload("bijian", "/fat1/", file.getOriginalFilename(), null);
        return false;
    }

    @GetMapping(value = "/exists")
    @ApiOperation("文件是否存在")
    @ResponseBody
    public DefaultResponse<Boolean> exists(String id) {
        //        return new DefaultResponse(fileService.exists(id));
        return null;
    }

    @PostMapping(value = "/delete")
    @ApiOperation("文件删除")
    @ResponseBody
    public DefaultResponse<Object> delete(@RequestBody ReqDelete reqDelete) {
        //        return new DefaultResponse(fileService.delete(reqDelete));
        return null;
    }
}
