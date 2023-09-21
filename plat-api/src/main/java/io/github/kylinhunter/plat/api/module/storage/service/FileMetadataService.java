/*
 * Copyright (C) 2023 The k-commons Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.kylinhunter.plat.api.module.storage.service;

import io.github.kylinhunter.plat.api.module.storage.bean.entity.FileMetadata;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqCreate;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqQuery;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataReqUpdate;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataResp;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileMetadataVO;
import io.github.kylinhunter.plat.api.service.local.CommonService;

/**
 * FileMetadataService 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-06-30
 */
public interface FileMetadataService
    extends CommonService<
        FileMetadata,
        FileMetadataReqCreate,
        FileMetadataReqUpdate,
        FileMetadataResp,
        FileMetadataVO,
        FileMetadataReqQuery> {

  FileMetadata findByMd5(String md5);

  FileMetadata findByMd5AndName(String md5, String name);
}
