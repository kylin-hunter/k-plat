package com.kylinhunter.plat.kb.service.local.imp;

import com.kylinhunter.plat.api.module.kb.bean.entity.Doc;
import com.kylinhunter.plat.api.module.kb.bean.vo.DocReqCreate;
import com.kylinhunter.plat.api.module.kb.bean.vo.DocReqQuery;
import com.kylinhunter.plat.api.module.kb.bean.vo.DocReqUpdate;
import com.kylinhunter.plat.api.module.kb.bean.vo.DocResp;
import com.kylinhunter.plat.api.module.kb.bean.vo.DocVO;
import com.kylinhunter.plat.kb.service.local.DocService;
import com.kylinhunter.plat.kb.dao.mapper.DocMapper;
import com.kylinhunter.plat.dao.service.local.CommonServiceImpl;

import org.springframework.stereotype.Service;

/**
 * <p>
 * DocServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-19
 */
@Service
public class DocServiceImp
        extends CommonServiceImpl<DocMapper, Doc,
        DocReqCreate, DocReqUpdate,
        DocResp, DocVO, DocReqQuery> implements DocService {

}