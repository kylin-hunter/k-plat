package com.kylinhunter.plat.kb.core.service.local;

import com.kylinhunter.plat.kb.api.module.core.bean.entity.Doc;
import com.kylinhunter.plat.kb.api.module.core.bean.vo.DocReqCreate;
import com.kylinhunter.plat.kb.api.module.core.bean.vo.DocReqQuery;
import com.kylinhunter.plat.kb.api.module.core.bean.vo.DocReqUpdate;
import com.kylinhunter.plat.kb.api.module.core.bean.vo.DocResp;
import com.kylinhunter.plat.kb.api.module.core.bean.vo.DocVO;
import com.kylinhunter.plat.api.service.local.CommonService;

/**
 * <p>
 * DocService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-20
 */
public interface DocService extends CommonService<Doc,
    DocReqCreate, DocReqUpdate,
    DocResp, DocVO, DocReqQuery>  {

}
