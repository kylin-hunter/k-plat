package io.github.kylinhunter.plat.kb.core.service.local;

import io.github.kylinhunter.plat.kb.api.module.core.bean.entity.Doc;
import io.github.kylinhunter.plat.kb.api.module.core.bean.vo.DocReqCreate;
import io.github.kylinhunter.plat.kb.api.module.core.bean.vo.DocReqQuery;
import io.github.kylinhunter.plat.kb.api.module.core.bean.vo.DocReqUpdate;
import io.github.kylinhunter.plat.kb.api.module.core.bean.vo.DocResp;
import io.github.kylinhunter.plat.kb.api.module.core.bean.vo.DocVO;
import io.github.kylinhunter.plat.api.service.local.CommonService;

/**
 * <p>
 * DocService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-07-06
 */
public interface DocService extends CommonService<Doc,
    DocReqCreate, DocReqUpdate,
    DocResp, DocVO, DocReqQuery>  {

}
