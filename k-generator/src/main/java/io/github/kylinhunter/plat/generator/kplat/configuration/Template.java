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
package io.github.kylinhunter.plat.generator.kplat.configuration;

import io.github.kylinhunter.commons.io.file.FileExtensions;
import lombok.Getter;

/**
 * @author BiJi'an
 * @description 模板类型
 * @date 2022/01/01
 */
@Getter
public enum Template {
  VO_CREATE(TemplateType.VO, "", "ReqCreate", FileExtensions.JAVA),
  VO_UPDATE(TemplateType.VO, "", "ReqUpdate", FileExtensions.JAVA),
  VO_RESPONSE(TemplateType.VO, "", "Resp", FileExtensions.JAVA),
  VO_REQ_QUREY(TemplateType.VO, "", "ReqQuery", FileExtensions.JAVA),
  VO(TemplateType.VO, "", "VO", FileExtensions.JAVA),
  SERVICE_LOCAL(TemplateType.SERVICE, "", "Service", FileExtensions.JAVA),
  SERVICE_LOCAL_IMP(TemplateType.SERVICE, "", "ServiceImp", FileExtensions.JAVA),
  SERVICE_INTERCEPTOR_SAVE_UPDATE(
      TemplateType.SERVICE_INTERCEPTOR, "", "SaveOrUpdateInterceptor", FileExtensions.JAVA),
  SERVICE_INTERCEPTOR_DELETE(
      TemplateType.SERVICE_INTERCEPTOR, "", "DeleteInterceptor", FileExtensions.JAVA),
  //    SERVICE_RPC(TemplateType.SERVICE, "Rpc", "Service"),
  //    SERVICE_RPC_IMP(TemplateType.SERVICE, "Rpc", "ServiceImp"),
  CONTROLLER(TemplateType.CONTROLLER, "", "Controller", FileExtensions.JAVA);
  private final TemplateType type;
  private final String suffix;
  private final String prefix;
  private final String extension;

  Template(TemplateType type, String prefix, String suffix, String extension) {
    this.type = type;
    this.prefix = prefix;
    this.suffix = suffix;
    this.extension = extension;
  }

  public String getName(String entityName, boolean extension) {
    if (extension) {
      return this.prefix + entityName + this.suffix + this.extension;

    } else {
      return this.prefix + entityName + this.suffix;
    }
  }
}
