package com.kylinhunter.plat.kb.service.local;

import com.kylinhunter.plat.api.module.kb.bean.entity.Doc;
import com.kylinhunter.plat.api.module.kb.bean.vo.DocReqCreate;
import com.kylinhunter.plat.api.module.kb.bean.vo.DocReqQuery;
import com.kylinhunter.plat.api.module.kb.bean.vo.DocReqUpdate;
import com.kylinhunter.plat.api.module.kb.bean.vo.DocResp;
import com.kylinhunter.plat.api.module.kb.bean.vo.DocVO;
import com.kylinhunter.plat.api.service.local.CommonService;

/**
 * <p>
 * DocService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-19
 */
public interface DocService extends CommonService<Doc,
    DocReqCreate, DocReqUpdate,
    DocResp, DocVO, DocReqQuery>  {

}
