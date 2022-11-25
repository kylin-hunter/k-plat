package io.github.kylinhunter.plat.generator.kplat.configuration;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.reflections.ReflectionUtils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.github.kylinhunter.plat.generator.kplat.configuration.bean.EntityField;
import io.github.kylinhunter.plat.generator.kplat.configuration.bean.OutputInfo;
import io.github.kylinhunter.plat.generator.kplat.convertor.FieldConvert;

import io.github.kylinhunter.commons.component.CF;
import io.github.kylinhunter.commons.name.NCStrategy;
import io.github.kylinhunter.commons.name.NameConvertors;
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

    private final CodeContext codeContext;
    private final NameConvertors nameConvertors = CF.get(NameConvertors.class);

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

        outputInfo.setMapperClass(strategyConfigs.getMapperClasses().get(entityClass));
        String entityName = entityClass.getSimpleName();

        outputInfo.setEntityName(entityName);
        outputInfo.setEntitySnakeName(nameConvertors.convert(NCStrategy.CAMEL_TO_SNAKE, entityName));
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

        log.info("buildOutputInfo success,template:" + template + ",entityClass:" + entityClass.getName());
        return outputInfo;

    }

}
