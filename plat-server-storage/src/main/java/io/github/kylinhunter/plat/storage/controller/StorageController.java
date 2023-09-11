/*
 * Copyright (C) 2023 The k-commons Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kylinhunter.plat.storage.controller;

import io.github.kylinhunter.plat.storage.service.local.StorageService;
import io.github.kylinhunter.plat.web.controller.CommonController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-27 01:45
 */
@RestController
@RequestMapping("/api/v1/storage")
@Api(value = "Storage相关接口")
@RequiredArgsConstructor
public class StorageController extends CommonController {

  private final StorageService storageService;

  @ApiOperation(value = "needUpload", notes = "上传")
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
