package com.kylinhunter.plat.kb.core.service.local.interceptor;

import com.kylinhunter.plat.kb.api.module.core.bean.entity.Doc;
import com.kylinhunter.plat.kb.api.module.core.bean.vo.DocReqCreate;
import com.kylinhunter.plat.kb.api.module.core.bean.vo.DocReqQuery;
import com.kylinhunter.plat.kb.api.module.core.bean.vo.DocReqUpdate;
import com.kylinhunter.plat.kb.api.module.core.bean.vo.DocResp;
import com.kylinhunter.plat.kb.api.module.core.bean.vo.DocVO;
import com.kylinhunter.plat.dao.service.local.interceptor.SaveOrUpdateInterceptor;
import org.springframework.stereotype.Component;


/**
 * <p>
 * DocSaveOrUpdateInterceptor 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-07-06
 */
@Component
public class DocSaveOrUpdateInterceptor extends SaveOrUpdateInterceptor<Doc,
    DocReqCreate, DocReqUpdate,
    DocResp, DocVO, DocReqQuery>  {

}
