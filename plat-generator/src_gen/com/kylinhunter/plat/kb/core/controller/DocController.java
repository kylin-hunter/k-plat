package com.kylinhunter.plat.kb.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.kylinhunter.plat.kb.api.module.core.bean.entity.Doc;
import com.kylinhunter.plat.kb.api.module.core.bean.vo.DocReqCreate;
import com.kylinhunter.plat.kb.api.module.core.bean.vo.DocReqQuery;
import com.kylinhunter.plat.kb.api.module.core.bean.vo.DocReqUpdate;
import com.kylinhunter.plat.kb.api.module.core.bean.vo.DocResp;
import com.kylinhunter.plat.kb.api.module.core.bean.vo.DocVO;
import com.kylinhunter.plat.kb.core.service.local.DocService;
import com.kylinhunter.plat.web.controller.CommonCurdController;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * DocController  代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-07-11
 */
@RestController
@RequestMapping("/api/v1/core/docs")
@Api(value = "Doc相关接口")
@RequiredArgsConstructor
public class DocController extends
        CommonCurdController<DocService,DocReqCreate,
            DocReqUpdate, DocResp, DocVO, DocReqQuery,Doc> {

}