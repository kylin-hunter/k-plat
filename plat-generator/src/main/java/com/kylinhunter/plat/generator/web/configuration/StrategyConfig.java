package com.kylinhunter.plat.generator.web.configuration;

import java.util.Set;

import org.apache.commons.lang3.ClassUtils;

import com.google.common.collect.Sets;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description 策略配置项
 * @author BiJi'an
 * @date   2021/8/4
 **/
@Data
@Accessors(chain = true)
public class StrategyConfig {
    private boolean lombok = false; /*是否为lombok模型（默认 false*/
    private boolean lombokChainModel = false; /*是否为链式模型（默认 false）*/

    private String superClass; /*自定义继承的Vo类全称，带包名*/
    private String superClassName; /*自定义继承的Vo类名字 */
    private String namePattern; /*名称方式，%s 为占位符 例如： %sVO生成 UserVO*/

    private boolean serializable = false;  /*序列化支持*/
    private Set<String> skipFields = Sets.newHashSet();

    public void addSkipField(String skipField) {
        skipFields.add(skipField);
    }

    /**
     * @param superClass 父类
     * @return com.kylinhunter.plat.generator.custom.configuration.StrategyConfig
     * @throws
     * @title 设置父类
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/4 4:49 下午
     */
    public StrategyConfig setSuperClass(Class<?> superClass) {
        this.superClass = superClass.getCanonicalName();
        this.superClassName = ClassUtils.getShortClassName(this.superClass);
        return this;
    }

    /**
     * @param superClass
     * @return com.kylinhunter.plat.generator.custom.configuration.StrategyConfig
     * @throws
     * @title 设置父类
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/5 9:47 下午
     */

    public StrategyConfig setSuperClass(String superClass) {
        this.superClass = superClass;
        this.superClassName = ClassUtils.getShortClassName(this.superClass);
        return this;
    }

}
