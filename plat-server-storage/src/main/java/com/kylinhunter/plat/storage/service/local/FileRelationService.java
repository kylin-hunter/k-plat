package com.kylinhunter.plat.storage.service.local;

import io.github.kylinhunter.plat.api.module.storage.bean.entity.FileRelation;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationReqCreate;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationReqQuery;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationReqUpdate;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationResp;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationVO;
import io.github.kylinhunter.plat.api.service.local.CommonService;

/**
 * <p>
 * FileRelationService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-07-05
 */
public interface FileRelationService extends CommonService<FileRelation,
        FileRelationReqCreate, FileRelationReqUpdate,
        FileRelationResp, FileRelationVO, FileRelationReqQuery> {

    int countByFileId(String fileId);

}
