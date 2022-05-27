package com.kylinhunter.plat.generator.core.configuration;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.kylinhunter.plat.commons.util.ReflectionUtil;
import com.kylinhunter.plat.commons.util.name.NCStrategy;
import com.kylinhunter.plat.commons.util.name.NamingConvertors;
import com.kylinhunter.plat.generator.core.configuration.bean.EntityField;
import com.kylinhunter.plat.generator.core.configuration.bean.OutputInfo;
import com.kylinhunter.plat.generator.core.convertor.FieldConvert;
import com.kylinhunter.plat.generator.core.convertor.FieldConvertRegister;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description 全部配置信息的处理工具
 * @date 2021/8/4
 **/
@Data
@Slf4j
public class CodeContextBuilder {

    private CodeContext codeContext = new CodeContext();

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
     * @param template    template
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

        String entityName = entityClass.getSimpleName();
        TemplateType templateType = template.getType();

        outputInfo.setEntityName(entityName);
        outputInfo.setEntitySnakeName(NamingConvertors.convert(NCStrategy.CAMEL_TO_SNAKE, entityName));
        // 处理包
        outputInfo.setPackageName(packageConfig.getPackage(template));
        outputInfo.setPackagePath(packageConfig.getPackagePath(template));

        if (globalConfig.isSwagger2()) {
            outputInfo.addImportPackages(ApiModel.class.getCanonicalName());

        }
        if (strategyConfig.isFiledSwagger2()) {
            outputInfo.addImportPackages(ApiModelProperty.class.getCanonicalName());
        }
        if (strategyConfig.isLombok()) {
            outputInfo.addImportPackages(Data.class.getCanonicalName());
            outputInfo.addImportPackages(EqualsAndHashCode.class.getCanonicalName());

        }
        if (strategyConfig.isLombokChainModel()) {
            outputInfo.addImportPackages(Accessors.class.getCanonicalName());
        }

        // 处理类名
        outputInfo.setClassName(strategyConfig.getClassName(entityName));

        // 处理父类
        if (StringUtils.isNotBlank(strategyConfig.getSuperClass())) {
            outputInfo.addImportPackages(strategyConfig.getSuperClass());
        } else {
            if (strategyConfig.isSerializable()) {
                outputInfo.addImportPackages(Serializable.class.getCanonicalName());
            }
        }

        // 处理fields
        FieldConvert fieldConvert = FieldConvertRegister.get(templateType);
        for (Field field : ReflectionUtil.getAllDeclaredFields(entityClass, true).values()) {
            EntityField entityField = fieldConvert.process(strategyConfig, field);
            if (entityField != null) {
                if (!entityField.isPrimitive() && !entityField.getClassName().startsWith("java.lang")) {
                    outputInfo.addImportPackages(entityField.getClassName());
                }
                outputInfo.addEntityField(entityField);
            }

        }

        log.info("buildOutputInfo success,template:" + template + ",entityClass:" + entityClass.getName());
        return outputInfo;

    }

}
