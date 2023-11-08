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
package io.github.kylinhunter.plat.generator.kplat;

import io.github.kylinhunter.plat.generator.kplat.configuration.CodeContext;
import io.github.kylinhunter.plat.generator.kplat.configuration.CodeContextBuilder;
import io.github.kylinhunter.plat.generator.kplat.configuration.Configurations;
import io.github.kylinhunter.plat.generator.kplat.engine.AbstractTemplateEngine;
import io.github.kylinhunter.plat.generator.kplat.engine.VelocityTemplateEngine;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 */
@Slf4j
@Data
public class KPlatCodeGennerator {

  protected CodeContextBuilder codeContextBuilder; /*配置信息*/
  private AbstractTemplateEngine templateEngine; /*模板引擎*/

  public KPlatCodeGennerator withConfigurations(Configurations configurations) {
    this.codeContextBuilder = new CodeContextBuilder(configurations.getCodeContext());
    templateEngine = new VelocityTemplateEngine(); /* 默认模板引擎采用 Velocity */

    return this;
  }

  /**
   * @return void
   * @title 生成代码
   * @description
   * @author BiJi'an
   * @date 2021/8/4 8:43 下午
   */
  public void execute() {
    log.info("==========================准备生成文件...==========================");
    CodeContext build = codeContextBuilder.build();
    templateEngine.init(build).mkdirs().batchOutput().open();
    log.info("==========================文件生成完成！！！==========================");
  }
}
