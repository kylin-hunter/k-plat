package com.kylinhunter.plat.generator.kplat.configuration;

import java.io.File;
import java.nio.file.Path;
import java.util.Set;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Sets;

import jodd.util.StringPool;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author BiJi'an
 * @description 策略配置项
 * @date 2021/8/4
 **/
@Data
@Accessors(chain = true)
public class StrategyConfig {
    private final Template template;
    private final GlobalConfig globalConfig;
    private final PackageConfig packageConfig;
    private boolean filedSwagger2 = true;
    private boolean lombok = false; /*是否为lombok模型（默认 false*/
    private boolean lombokChainModel = false; /*是否为链式模型（默认 false）*/

    private String superClassName; /*自定义继承的Vo类全称，带包名*/
    private String superClassSimpleName; /*自定义继承的Vo类名字 */
    private String classNamePattern; /*名称方式，%s 为占位符 例如： %sVO生成 UserVO*/

    private boolean serializable = false;  /*序列化支持*/
    private Set<String> skipFields = Sets.newHashSet();

    public void addSkipField(String skipField) {
        skipFields.add(skipField);
    }

    /**
     * @param superClassName 父类
     * @return com.kylinhunter.plat.generator.custom.configuration.StrategyConfig
     * @title 设置父类
     * @description
     * @author BiJi'an
     * @date 2021/8/4 4:49 下午
     */
    @SuppressWarnings("UnusedReturnValue")
    public StrategyConfig setSuperClassName(Class<?> superClassName) {
        this.superClassName = superClassName.getCanonicalName();
        this.superClassSimpleName = superClassName.getSimpleName();
        return this;
    }

    /**
     * @param superClass superClassName
     * @return com.kylinhunter.plat.generator.custom.configuration.StrategyConfig
     * @title 设置父类
     * @description
     * @author BiJi'an
     * @date 2021/8/5 9:47 下午
     */

    @SuppressWarnings("unused")
    public StrategyConfig setSuperClassName(String superClass) {
        this.superClassName = superClass;
        this.superClassSimpleName = ClassUtils.getShortClassName(this.superClassName);
        return this;
    }

    public String getClassName(String entityName) {
        if (StringUtils.isNotBlank(classNamePattern)) {
            return String.format(classNamePattern, entityName);
        } else {
            return template.getName(entityName, false);
        }
    }

    public Path getDistFilePath(Template template, String entityName) {
        Path outputDir = globalConfig.getOutputDir(template);
        String packageRelativePath = getDistFileRelativePath(template, entityName);
        return outputDir.resolve(packageRelativePath);
    }

    public String getDistFileRelativePath(Template template, String entityName) {
        String packageName = packageConfig.getPackage(template);
        String packageRelativePath = packageName.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
        String classRelatviePath =
                packageRelativePath + File.separator + this.getClassName(entityName) + "." + template.getExtension();
        return classRelatviePath;
    }

}
