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

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.kylinhunter.commons.name.CamelToSnakeUtils;
import io.github.kylinhunter.plat.generator.kplat.configuration.bean.EntityField;
import io.github.kylinhunter.plat.generator.kplat.configuration.bean.OutputInfo;
import io.github.kylinhunter.plat.generator.kplat.convertor.FieldConvert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.lang.reflect.Field;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.reflections.ReflectionUtils;

/**
 * @author BiJi'an
 * @description 全部配置信息的处理工具
 * @date 2021/8/4
 */
@Data
@Slf4j
public class CodeContextBuilder {

  private final CodeContext codeContext;

  /**
   * @return ConfigCentor
   * @title 初始化
   * @description
   * @author BiJi'an
   * @date 2021/8/4 8:37 下午
   */
  public CodeContext build() {

    TemplateConfig templateConfig = this.codeContext.getTemplateConfig();
    StrategyConfigs strategyConfigs = this.codeContext.getStrategyConfigs();

    for (Template template : Template.values()) {
      if (templateConfig.isEnabled(template)) {
        for (Class<?> entityClass : strategyConfigs.getEntityClasses()) {
          this.codeContext.addOutputInfo(template, buildOutputInfo(template, entityClass));
        }
      }
    }

    return this.codeContext;
  }

  /**
   * @param template template
   * @param entityClass entityClass
   * @return void
   * @title 处理模板需要的信息
   * @description
   * @author BiJi'an
   * @date 2021/8/4 7:59 下午
   */
  private OutputInfo buildOutputInfo(Template template, Class<?> entityClass) {

    OutputInfo outputInfo = new OutputInfo(template, entityClass);

    StrategyConfigs strategyConfigs = this.codeContext.getStrategyConfigs();
    StrategyConfig strategyConfig = strategyConfigs.get(template);
    PackageConfig packageConfig = this.codeContext.getPackageConfig();
    GlobalConfig globalConfig = this.codeContext.getGlobalConfig();

    outputInfo.setMapperClass(strategyConfigs.getMapperClasses().get(entityClass));
    String entityName = entityClass.getSimpleName();

    outputInfo.setEntityName(entityName);

    outputInfo.setEntitySnakeName(CamelToSnakeUtils.convert(entityName));
    // 处理包
    outputInfo.setPackageName(packageConfig.getPackage(template));
    outputInfo.setPackagePath(packageConfig.getPackagePath(template));
    outputInfo.setDistFilePath(strategyConfig.getDistFilePath(template, entityName));

    if (globalConfig.isSwagger2()) {
      outputInfo.addImportPackageForReq(ApiModel.class.getCanonicalName());
    }
    if (strategyConfig.isFiledSwagger2()) {
      outputInfo.addImportPackageForReq(ApiModelProperty.class.getCanonicalName());
    }
    if (strategyConfig.isLombok()) {
      outputInfo.addImportPackageForReq(Data.class.getCanonicalName());
      outputInfo.addImportPackageForReq(EqualsAndHashCode.class.getCanonicalName());
    }
    if (strategyConfig.isLombokChainModel()) {
      outputInfo.addImportPackageForReq(Accessors.class.getCanonicalName());
    }

    // 处理类名
    outputInfo.setClassName(strategyConfig.getClassName(entityName));

    // 处理父类
    if (StringUtils.isNotBlank(strategyConfig.getSuperClassName())) {
      outputInfo.addImportPackageForReq(strategyConfig.getSuperClassName());
    } else {
      if (strategyConfig.isSerializable()) {
        outputInfo.addImportPackageForReq(Serializable.class.getCanonicalName());
      }
    }

    // 处理fields
    FieldConvert fieldConvert = strategyConfigs.getFieldConvert();
    for (Field field : ReflectionUtils.getFields(entityClass)) {
      EntityField entityField = fieldConvert.convert(strategyConfig, entityClass, field);
      if (entityField != null) {
        if (!entityField.isPrimitive() && !entityField.getClassName().startsWith("java.lang")) {
          outputInfo.addImportPackageForReq(entityField.getClassName());
          outputInfo.addImportPackageForVO(entityField.getClassName());
        }
        outputInfo.addEntityField(entityField);
      }
    }

    log.info(
        "buildOutputInfo success,template:" + template + ",entityClass:" + entityClass.getName());
    return outputInfo;
  }
}
