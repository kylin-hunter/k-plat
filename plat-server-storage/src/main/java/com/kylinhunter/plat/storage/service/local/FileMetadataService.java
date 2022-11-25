package com.kylinhunter.plat.storage.service.local;

import io.github.kylinhunter.plat.api.module.storage.bean.entity.FileMetadata;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqCreate;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqQuery;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqUpdate;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataResp;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataVO;
import io.github.kylinhunter.plat.api.service.local.CommonService;

/**
 * <p>
 * FileMetadataService 代码工具自动生成，按需扩展
 * </p>
 *
 * @author biji'an
 * @since 2022-06-30
 */
public interface FileMetadataService extends CommonService<FileMetadata,
        FileMetadataReqCreate, FileMetadataReqUpdate,
        FileMetadataResp, FileMetadataVO, FileMetadataReqQuery> {

    FileMetadata findByMd5(String md5);
    FileMetadata findByMd5AndName(String md5,String name);

}
