package com.kylinhunter.plat.generator.core.configuration;

import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kylinhunter.plat.generator.core.configuration.bean.OutputInfo;

import lombok.Data;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 11:25
 **/
@Data
public class CodeContext {

    private GlobalConfig globalConfig = new GlobalConfig(); /*全局配置信息*/

    private PackageConfig packageConfig = new PackageConfig(globalConfig); /*package配置信息*/

    private TemplateConfig templateConfig = new TemplateConfig(); /*模板路径配置信息*/

    private StrategyConfigs strategyConfigs = new StrategyConfigs();  /*策略配置*/

    private Map<Template, List<OutputInfo>> allOutputInfos = Maps.newHashMap();/*output信息*/

    public void addOutputInfo(Template template, OutputInfo outputInfo) {
        this.allOutputInfos.compute(template, (k, v) -> {
            if (v == null) {
                v = Lists.newArrayList();
            }
            v.add(outputInfo);
            return v;
        });
    }

    /**
     * @param template template
     * @return java.util.List<com.kylinhunter.plat.generator.cskb.pojo.GenerateFileInfo>
     * @title 获取即将生成的代码文件的信息
     * @description
     * @author BiJi'an
     * @date 2022/01/01 3:08 下午
     */
    public List<OutputInfo> getOutputInfos(Template template) {
        return this.allOutputInfos.getOrDefault(template, Collections.emptyList());
    }

    /**
     * @return java.util.Set<java.nio.file.Path>
     * @title 计算全部路径
     * @description
     * @author BiJi'an
     * @date 2022/01/01 5:22 下午
     */
    public Set<Path> getAllPackagePath() {
        return this.allOutputInfos.values()
                .stream().flatMap(Collection::stream)
                .map(OutputInfo::getPackagePath)
                .collect(Collectors.toSet());

    }
}
