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

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.google.common.collect.Maps;
import io.github.kylinhunter.commons.strings.StringConst;
import java.io.File;
import java.nio.file.Path;
import java.util.Map;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

/**
 * @author BiJi'an
 * @description 包相关的配置项
 * @date 2022/01/01
 */
@Data
@Accessors(chain = true)
public class PackageConfig {
  private static final String DEFAULT_VO = "vo";
  private static final String DEFAULT_SERVICE = "service";
  private static final String DEFAULT_INTERCEPTER = "interceptor";
  private static final String DEFAULT_ONTROLLER = "controller";

  private final GlobalConfig globalConfig;
  private String parentPattern = "io.github.kylinhunter.plat"; /*父包名*/
  private Map<Template, String> packagePatterns = Maps.newHashMap();

  public PackageConfig(GlobalConfig globalConfig) {
    this.globalConfig = globalConfig;
    setPackagePattern(Template.VO_CREATE, DEFAULT_VO);
    setPackagePattern(Template.VO_UPDATE, DEFAULT_VO);
    setPackagePattern(Template.VO_RESPONSE, DEFAULT_VO);
    setPackagePattern(Template.VO_REQ_QUREY, DEFAULT_VO);
    setPackagePattern(Template.VO, DEFAULT_VO);
    setPackagePattern(Template.SERVICE_LOCAL, DEFAULT_SERVICE);
    setPackagePattern(Template.SERVICE_LOCAL_IMP, DEFAULT_SERVICE);
    setPackagePattern(Template.SERVICE_INTERCEPTOR_SAVE_UPDATE, DEFAULT_INTERCEPTER);
    setPackagePattern(Template.SERVICE_INTERCEPTOR_DELETE, DEFAULT_INTERCEPTER);
    //        setPackagePattern(Template.SERVICE_RPC, DEFAULT_SERVICE);
    //        setPackagePattern(Template.SERVICE_RPC_IMP, DEFAULT_SERVICE);
    setPackagePattern(Template.CONTROLLER, DEFAULT_ONTROLLER);
  }

  public void setPackagePattern(Template template, String pkg) {
    if (StringUtils.isNotBlank(pkg)) {
      this.packagePatterns.put(template, pkg);
    }
  }

  /**
   * @param template template
   * @return java.lang.String
   * @title 获取包名
   * @description
   * @author BiJi'an
   * @date 2022/01/01 4:31 下午
   */
  public String getPackage(Template template) {
    String parentPackage = getParentPackage();
    String currentPackage =
        String.format(this.packagePatterns.get(template), globalConfig.getModuleName());
    String packageName = (parentPackage + StringConst.DOT + currentPackage);
    packageName = packageName.replaceAll("\\.\\.", "\\.");
    return packageName;
  }

  public String getImport(StrategyConfig strategyConfig, String entityName) {
    String pkg = this.getPackage(strategyConfig.getTemplate());
    return pkg + "." + strategyConfig.getClassName(entityName);
  }

  public String getParentPackage() {
    return String.format(this.parentPattern, globalConfig.getModuleName());
  }

  public String getParentPackageRelativePath() {
    String packageName = this.getParentPackage();
    String packageRelativePath =
        packageName.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
    return packageRelativePath;
  }

  /**
   * @param template template
   * @return java.nio.file.Path
   * @title 获取包的路径
   * @description
   * @author BiJi'an
   * @date 2022/01/01 4:31 下午
   */
  public Path getPackagePath(Template template) {
    Path outputDir = globalConfig.getOutputDir(template);
    String packageRelativePath = getPackageRelativePath(template);
    return outputDir.resolve(packageRelativePath);
  }

  public String getPackageRelativePath(Template template) {
    String packageName = this.getPackage(template);
    String packageRelativePath =
        packageName.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
    return packageRelativePath;
  }
}
