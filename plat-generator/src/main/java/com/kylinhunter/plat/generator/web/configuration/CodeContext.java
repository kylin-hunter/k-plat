package com.kylinhunter.plat.generator.web.configuration;

import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.kylinhunter.plat.generator.web.pojo.OutputInfo;

import lombok.Data;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-10 11:25
 **/
@Data
public class CodeContext {

    private GlobalConfig globalConfig = new GlobalConfig(); /*全局配置信息*/

    private PackageConfig packageConfig = new PackageConfig(globalConfig); /*package配置信息*/

    private TemplateConfig templateConfig = new TemplateConfig(); /*模板路径配置信息*/

    private StrategyConfigs strategyConfigs = new StrategyConfigs();  /*策略配置*/

    private Map<Template, List<OutputInfo>> allOutputInfos; /*entity信息*/

    /**
     * @param template
     * @return java.util.List<com.kylinhunter.plat.generator.cskb.pojo.GenerateFileInfo>
     * @throws
     * @title 获取即将生成的代码文件的信息
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/6 3:08 下午
     */
    public List<OutputInfo> getOutputInfos(Template template) {
        return this.allOutputInfos.getOrDefault(template, Collections.emptyList());
    }

    /**
     * @return java.util.Set<java.nio.file.Path>
     * @throws
     * @title 计算全部路径
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/6 5:22 下午
     */
    public Set<Path> calAllPaths() {
        return this.allOutputInfos.values()
                .stream().flatMap(e -> e.stream())
                .map(e -> e.getPackagePath())
                .collect(Collectors.toSet());

    }
}
