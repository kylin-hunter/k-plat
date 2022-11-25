package io.github.kylinhunter.plat.kb.core.service.local.imp;

import org.springframework.stereotype.Service;

import io.github.kylinhunter.plat.dao.service.local.CommonServiceImpl;
import io.github.kylinhunter.plat.kb.api.module.core.bean.entity.Doc;
import io.github.kylinhunter.plat.kb.api.module.core.bean.vo.DocReqCreate;
import io.github.kylinhunter.plat.kb.api.module.core.bean.vo.DocReqQuery;
import io.github.kylinhunter.plat.kb.api.module.core.bean.vo.DocReqUpdate;
import io.github.kylinhunter.plat.kb.api.module.core.bean.vo.DocResp;
import io.github.kylinhunter.plat.kb.api.module.core.bean.vo.DocVO;
import io.github.kylinhunter.plat.kb.core.dao.mapper.DocMapper;
import io.github.kylinhunter.plat.kb.core.service.local.DocService;
import io.github.kylinhunter.plat.kb.core.service.local.interceptor.DocDeleteInterceptor;
import io.github.kylinhunter.plat.kb.core.service.local.interceptor.DocSaveOrUpdateInterceptor;

/**
 * <p>
 * DocServiceImp 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-07-06
 */
@Service
public class DocServiceImp
        extends CommonServiceImpl<DocMapper, Doc,
        DocReqCreate, DocReqUpdate,
        DocResp, DocVO, DocReqQuery> implements DocService {

    public DocServiceImp(DocSaveOrUpdateInterceptor saveOrUpdateInterceptor,
                         DocDeleteInterceptor deleteInterceptor) {
        this.saveOrUpdateInterceptor = saveOrUpdateInterceptor;
        this.deleteInterceptor = deleteInterceptor;
    }

}