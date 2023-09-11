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
package io.github.kylinhunter.plat.storage.service.local;

import io.github.kylinhunter.plat.api.module.storage.bean.entity.FileRelation;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationReqCreate;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationReqQuery;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationReqUpdate;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationResp;
import io.github.kylinhunter.plat.api.module.storage.bean.vo.FileRelationVO;
import io.github.kylinhunter.plat.api.service.local.CommonService;

/**
 * FileRelationService 代码工具自动生成，按需扩展
 *
 * @author biji'an
 * @since 2022-07-05
 */
public interface FileRelationService
    extends CommonService<
        FileRelation,
        FileRelationReqCreate,
        FileRelationReqUpdate,
        FileRelationResp,
        FileRelationVO,
        FileRelationReqQuery> {

  int countByFileId(String fileId);
}
