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
package io.github.kylinhunter.plat.generator.mybatis;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import io.github.kylinhunter.commons.exception.embed.ParamException;
import io.github.kylinhunter.commons.io.file.UserDirUtils;
import java.io.File;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-02 21:09
 */
@Slf4j
@Data
@NoArgsConstructor
public class MyPlusGenerator {
  private MyPlusGeneratorConfig myPlusGeneratorConfig;

  public void exec(boolean clear) {

    this.check();
    AutoGenerator autoGenerator = new AutoGenerator();
    autoGenerator.setGlobalConfig(myPlusGeneratorConfig.getGlobalConfig());
    autoGenerator.setDataSource(myPlusGeneratorConfig.getDataSourceConfig());
    autoGenerator.setPackageInfo(myPlusGeneratorConfig.getPackageConfig());
    autoGenerator.setStrategy(myPlusGeneratorConfig.getStrategyConfig());
    autoGenerator.setTemplate(myPlusGeneratorConfig.getTemplateConfig());
    if (clear) {
      this.clear(autoGenerator.getGlobalConfig());
    }
    autoGenerator.execute();
  }

  private void check() {

    if (myPlusGeneratorConfig == null) {
      throw new ParamException("myPlusGeneratorConfig is null");
    }
  }

  public void clear(GlobalConfig globalConfig) {
    File outputDir = new File(globalConfig.getOutputDir());
    UserDirUtils.deleteQuietly(outputDir);
    log.info("delete outputDir=>" + outputDir.getAbsolutePath());
  }
}
