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
package io.github.kylinhunter.plat.search.service.local.dto;

import io.github.kylinhunter.plat.api.module.storage.bean.entity.FileMetadata;
import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-07-05 00:43
 */
@Data
public class UploadBeforeMsg {

  private boolean needUpload; // 是否上传
  private FileMetadata oldFileMetadata;
}
