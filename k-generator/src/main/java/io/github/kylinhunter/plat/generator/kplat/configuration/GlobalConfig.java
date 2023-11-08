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

import com.google.common.collect.Maps;
import io.github.kylinhunter.commons.exception.embed.InternalException;
import io.github.kylinhunter.commons.io.file.UserDirUtils;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author BiJi'an
 * @description 全局配置
 * @date 2022/01/01
 */
@Data
@Accessors(chain = true)
public class GlobalConfig {

  private String author; /* 开发人员*/

  private boolean swagger2 = false; /*开启 swagger2 模式*/

  private boolean clearBeforExec = false; /*执行前对 outputDir下的自定义子目录 清空 */

  private Path defaultOutputDir = UserDirUtils.getTmpDir(true, "auto_code").toPath(); /*生成文件的输出目录*/

  private boolean fileOverride = false; /*是否覆盖已有文件*/

  private boolean autoCreateOutputDir = false; /*是否自动创建outputDir*/

  private boolean open = true; /*是否打开输出目录*/
  private String moduleName = ""; /*模块名*/

  private Map<Template, Path> outputDirs = Maps.newHashMap();

  /**
   * @param outputDir outputDir
   * @return void
   * @title setOutputDir
   * @description
   * @author BiJi'an
   * @date 2022/01/01 3:45 下午
   */
  public void setDefaultOutputDir(File outputDir) {
    this.setDefaultOutputDir(outputDir.toPath());
  }

  /**
   * @param outputDir outputDir
   * @return void
   * @title setOutputDir
   * @description
   * @author BiJi'an
   * @date 2022/01/01 3:45 下午
   */
  public void setDefaultOutputDir(String outputDir) {
    this.setDefaultOutputDir(Paths.get(outputDir));
  }

  /**
   * @param outputDir outputDir
   * @return void
   * @title setOutputDir
   * @description
   * @author BiJi'an
   * @date 2022/01/01 3:45 下午
   */
  public void setDefaultOutputDir(Path outputDir) {
    if (Files.exists(outputDir)) {
      if (!Files.isDirectory(outputDir)) {
        throw new InternalException("invalid outputDir" + outputDir);
      }
    } else {
      if (autoCreateOutputDir) {
        try {
          Files.createDirectories(outputDir);
        } catch (IOException e) {
          throw new InternalException("create outputDir error" + outputDir, e);
        }
      } else {
        throw new InternalException(" outputDir no exist " + outputDir);
      }
    }
    this.defaultOutputDir = outputDir;
  }

  public void setOutputDir(Template template, File outputDir) {
    this.outputDirs.put(template, outputDir.toPath());
  }

  public void setOutputDir(Template template, String outputDir) {
    this.outputDirs.put(template, Paths.get(outputDir));
  }

  public void setOutputDir(Template template, Path outputDir) {
    this.outputDirs.put(template, outputDir);
  }

  public Path getOutputDir(Template template) {
    return this.outputDirs.getOrDefault(template, defaultOutputDir);
  }
}
