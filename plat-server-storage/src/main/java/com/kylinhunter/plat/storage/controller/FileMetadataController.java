package com.kylinhunter.plat.storage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.kylinhunter.plat.api.module.storage.bean.entity.FileMetadata;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqCreate;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqQuery;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqUpdate;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataResp;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataVO;
import com.kylinhunter.plat.storage.service.local.FileMetadataService;
import com.kylinhunter.plat.web.controller.CommonCurdController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * FileMetadataController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-30
 */
@RestController
@RequestMapping("/api/v1/storage/file_metadatas")
@Api(value = "FileMetadata相关接口")
@RequiredArgsConstructor
public class FileMetadataController extends
        CommonCurdController<FileMetadataService,FileMetadataReqCreate,
            FileMetadataReqUpdate, FileMetadataResp, FileMetadataVO, FileMetadataReqQuery,FileMetadata> {

}