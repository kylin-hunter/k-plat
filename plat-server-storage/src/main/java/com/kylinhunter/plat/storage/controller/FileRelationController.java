package com.kylinhunter.plat.storage.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.kylinhunter.plat.api.module.storage.bean.entity.FileRelation;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationReqCreate;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationReqQuery;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationReqUpdate;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationResp;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationVO;
import com.kylinhunter.plat.storage.service.local.FileRelationService;
import com.kylinhunter.plat.web.controller.CommonCurdController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * FileRelationController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-07-05
 */
@RestController
@RequestMapping("/api/v1/storage/file_relations")
@Api(value = "FileRelation相关接口")
@RequiredArgsConstructor
public class FileRelationController extends
        CommonCurdController<FileRelationService,FileRelationReqCreate,
            FileRelationReqUpdate, FileRelationResp, FileRelationVO, FileRelationReqQuery,FileRelation> {

}