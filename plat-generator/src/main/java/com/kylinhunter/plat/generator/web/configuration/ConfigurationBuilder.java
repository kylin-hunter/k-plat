package com.kylinhunter.plat.generator.web.configuration;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kylinhunter.plat.commons.util.ReflectionUtil;
import com.kylinhunter.plat.commons.util.name.NCStrategy;
import com.kylinhunter.plat.commons.util.name.NamingConvertors;
import com.kylinhunter.plat.generator.web.convertor.FieldConvert;
import com.kylinhunter.plat.generator.web.convertor.FieldConvertRegister;
import com.kylinhunter.plat.generator.web.pojo.EntityField;
import com.kylinhunter.plat.generator.web.pojo.OutputInfo;

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
public class ConfigurationBuilder {

    private CodeContext codeContext = new CodeContext();

    /**
     * @return ConfigCentor
     * @throws
     * @title 初始化
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/4 8:37 下午
     */
    public CodeContext init() {
        this.codeContext.setAllOutputInfos(this.initOutputInfos());
        return this.getCodeContext();
    }

    /**
     * @return java.util.List<com.kylinhunter.plat.generator.custom.pojo.EntityInfo>
     * @throws
     * @title 计算所有的 entity 信息
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/4 7:59 下午
     */
    private Map<Template, List<OutputInfo>> initOutputInfos() {
        Map<Template, List<OutputInfo>> allOutputInfos = Maps.newHashMap();
        TemplateConfig templateConfig = this.codeContext.getTemplateConfig();
        StrategyConfigs strategyConfigs = this.codeContext.getStrategyConfigs();

        for (Template template : Template.values()) {
            if (templateConfig.isEnabled(template)) {
                List<OutputInfo> outputInfos = Lists.newArrayList();
                allOutputInfos.put(template, outputInfos);
                for (Class<?> entityClass : strategyConfigs.getEntityClasses()) {
                    OutputInfo outputInfo = new OutputInfo(template, entityClass);
                    initOutputInfo(outputInfo);
                    outputInfos.add(outputInfo);

                }
            }
        }

        return allOutputInfos;

    }

    /**
     * @param
     * @return void
     * @throws
     * @title 处理模板需要的信息
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/4 7:59 下午
     */
    private void initOutputInfo(OutputInfo outputInfo) {

        Template template = outputInfo.getTemplate();
        StrategyConfigs strategyConfigs = this.codeContext.getStrategyConfigs();
        StrategyConfig strategyConfig = strategyConfigs.getStrategyConfig(template);
        PackageConfig packageConfig = this.codeContext.getPackageConfig();
        GlobalConfig globalConfig = this.codeContext.getGlobalConfig();

        Class<?> entityClass = outputInfo.getEntityClass();
        String entityName = entityClass.getSimpleName();
        TemplateType templateType = template.getType();

        outputInfo.setEntityName(entityName);
        outputInfo.setEntityNameUL(NamingConvertors.convert(NCStrategy.CAMEL_TO_SNAKE, entityName));
        // 处理包
        outputInfo.setPackageName(packageConfig.getPackage(template));
        outputInfo.setPackagePath(packageConfig.getPackagePath(template));

        if (globalConfig.isSwagger2()) {
            outputInfo.addImportPackages(ApiModel.class.getCanonicalName());
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
        if (StringUtils.isNotBlank(strategyConfig.getNamePattern())) {
            outputInfo.setClassName(String.format(strategyConfig.getNamePattern(), entityName));
        } else {
            outputInfo.setClassName(template.getPrefix() + entityName + template.getSuffix());
        }

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
                if (!entityField.isPrimitive() && !entityField.getTypeFullName().startsWith("java.lang")) {
                    outputInfo.addImportPackages(entityField.getTypeFullName());
                }
                //                if (entityField.isDatetime()) {
                //                    outputInfo.addImportPackages(JsonFormat.class.getCanonicalName());
                //                }
                outputInfo.addEntityField(entityField);
            }

        }
        log.info("initOutputInfo success");

    }

}
