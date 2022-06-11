package com.kylinhunter.plat.generator.kplat.configuration.bean;

import java.nio.file.Path;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.kylinhunter.plat.generator.kplat.configuration.Template;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author BiJi'an
 * @description 生成的文件信息
 * @date 2022/01/01
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OutputInfo {
    private final Template template; /*模板类型*/
    private final Class<?> entityClass; /*entity的Class*/
    private Class<?> mapperClass; /*mapper的Class*/
    private String entityName;/* entity名字*/
    private String entitySnakeName;/* entity 的 snake */

    @EqualsAndHashCode.Include
    private String packageName;/* 包名*/
    private Path packagePath;/* 包路径*/

    private Set<String> importPackages = Sets.newTreeSet();/* 引入的包*/
    @EqualsAndHashCode.Include
    private String className; /*类名*/

    private List<EntityField> entityFields = Lists.newArrayList(); /*entity的Field*/

    /**
     * @param entityField entityField
     * @return com.kylinhunter.plat.generator.cskb.pojo.GenerateFileInfo
     * @title 添加field
     * @description
     * @author BiJi'an
     * @date 2022/01/01 4:46 下午
     */
    public OutputInfo addEntityField(EntityField entityField) {
        entityFields.add(entityField);
        return this;
    }

    /**
     * @param pkg pkg
     * @return com.kylinhunter.plat.generator.cskb.pojo.GenerateFileInfo
     * @title 添加引入包
     * @description
     * @author BiJi'an
     * @date 2022/01/01 4:46 下午
     */
    public OutputInfo addImportPackages(String pkg) {
        importPackages.add(pkg);
        return this;
    }

}
