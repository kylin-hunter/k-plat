package com.kylinhunter.plat.generator.web.engine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.kylinhunter.plat.commons.exception.inner.InternalException;
import com.kylinhunter.plat.commons.util.DateUtils;
import com.kylinhunter.plat.generator.web.configuration.CodeContext;
import com.kylinhunter.plat.generator.web.configuration.GlobalConfig;
import com.kylinhunter.plat.generator.web.configuration.StrategyConfig;
import com.kylinhunter.plat.generator.web.configuration.Template;
import com.kylinhunter.plat.generator.web.configuration.TemplateConfig;
import com.kylinhunter.plat.generator.web.pojo.OutputInfo;

import jodd.util.StringPool;
import lombok.extern.slf4j.Slf4j;

/**
 * 模板引擎抽象类
 *
 * @author BiJi'an
 * @description
 * @date 2021/8/4
 **/
@Slf4j
public abstract class AbstractTemplateEngine {

    private CodeContext codeContext;

    /**
     * @param
     * @return com.kylinhunter.plat.generator.custom.AbstractTemplateEngine
     * @throws
     * @title 模板引擎初始化
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/4 8:15 下午
     */
    public AbstractTemplateEngine init(CodeContext CodeContext) {
        this.codeContext = CodeContext;
        return this;
    }

    /**
     * @return com.kylinhunter.plat.generator.custom.AbstractTemplateEngine
     * @throws
     * @title 创建目录
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/4 8:14 下午
     */
    public AbstractTemplateEngine mkdirs() {
        GlobalConfig globalConfig = codeContext.getGlobalConfig();
        Set<Path> allPaths = codeContext.calAllPaths();
        allPaths.forEach(path -> {
            if (Files.exists(path) && globalConfig.isClearBeforExec()) {
                FileUtils.deleteQuietly(path.toFile());
                log.debug("清空目录=>{}", path);
            }
        });
        allPaths.forEach(path -> {
            if (!Files.exists(path)) {
                try {
                    Files.createDirectories(path);
                    log.debug("创建目录=>{}", path);
                } catch (IOException e) {
                    throw new InternalException("创建目录失败", e);
                }
            }
        });

        return this;
    }

    /**
     * @return com.kylinhunter.plat.generator.custom.AbstractTemplateEngine
     * @throws
     * @title 输出结果
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/4 2:23 下午
     */
    public AbstractTemplateEngine batchOutput() {
        try {
            TemplateConfig templateConfig = codeContext.getTemplateConfig();
            for (Template template : Template.values()) {
                if (templateConfig.isEnabled(template)) {
                    for (OutputInfo outputInfo : codeContext.getOutputInfos(template)) {
                        Map<String, Object> objectMap = getObjectMap(outputInfo, template);
                        Path packagePath = outputInfo.getPackagePath();
                        Path filePath =
                                Paths.get(packagePath.toString(), outputInfo.getClassName() + fileSuffix());
                        String pathTemplate = pathTemplate(templateConfig.getTemplate(template));
                        if (isCreate(filePath) && StringUtils.isNotBlank(pathTemplate)) {
                            writer(objectMap, pathTemplate, filePath);
                        }

                    }
                }
            }

        } catch (Exception e) {
            log.error("无法创建文件，请检查配置信息！", e);
        }
        return this;
    }

    /**
     * @param path
     * @return java.lang.String
     * @throws
     * @title 模板真实文件路径
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/5 1:02 上午
     */
    public abstract String pathTemplate(String path);

    /**
     * @return java.lang.String
     * @throws
     * @title 文件后缀
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/5 1:02 上午
     */
    protected String fileSuffix() {
        return StringPool.DOT_JAVA;
    }

    /**
     * @param path
     * @return boolean
     * @throws
     * @title 检测文件是否需要生成
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/5 12:42 上午
     */
    protected boolean isCreate(Path path) throws IOException {

        // 全局判断【默认】
        boolean exist = Files.exists(path);
        if (!exist) {
            Files.createDirectories(path.getParent());
            return true;
        } else {
            return codeContext.getGlobalConfig().isFileOverride();

        }
    }

    /**
     * 将模板转化成为文件
     *
     * @param context      渲染对象 MAP 信息
     * @param templatePath 模板文件
     * @param path         文件生成的目录
     */
    public abstract void writer(Map<String, Object> context, String templatePath, Path path) throws Exception;

    public void open() {
        codeContext.getGlobalConfig().getOutputDirs().values().stream().distinct().forEach(dir -> {
            this.open(dir.toString());
        });

    }

    /**
     * @return void
     * @throws
     * @title 打开生成的代码目录
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/4 8:14 下午
     */
    public void open(String outputDir) {
        if (codeContext.getGlobalConfig().isOpen()
                && StringUtils.isNotBlank(outputDir)) {
            try {
                String osName = System.getProperty("os.className");
                if (osName != null) {
                    if (osName.contains("Mac")) {
                        //                        Runtime.getRuntime().exec("open " + outputDir);
                    } else if (osName.contains("Windows")) {
                        //                        Runtime.getRuntime().exec("cmd /c start " + outputDir);
                    } else {
                        log.debug("文件输出目录:" + outputDir);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param outputInfo
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @throws
     * @title 渲染对象 MAP 信息
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/4 8:23 下午
     */
    private Map<String, Object> getObjectMap(OutputInfo outputInfo, Template template) {
        Map<String, Object> objectMap = Maps.newHashMap();

        TemplateConfig templateConfig = codeContext.getTemplateConfig();

        objectMap.put("sys_TemplateType", template.getType());
        objectMap.put("sys_packageName", outputInfo.getPackageName());
        objectMap.put("sys_importPackages", outputInfo.getImportPackages());
        objectMap.put("sys_className", outputInfo.getClassName());
        objectMap.put("sys_comment", outputInfo.getClassName());
        objectMap.put("sys_entityFields", outputInfo.getEntityFields());
        objectMap.put("sys_date", DateUtils.toStringDate());

        StrategyConfig strategyConfig = codeContext.getStrategyConfigs().getStrategyConfig(template);
        objectMap.put("sys_isLombok", strategyConfig.isLombok());
        objectMap.put("sys_isLombokChainModel", strategyConfig.isLombokChainModel());
        objectMap.put("sys_haveSuperClass", StringUtils.isNotBlank(strategyConfig.getSuperClass()));
        objectMap.put("sys_superClass", strategyConfig.getSuperClass());
        objectMap.put("sys_superClassName", strategyConfig.getSuperClassName());
        objectMap.put("sys_serializable", strategyConfig.isSerializable());

        GlobalConfig globalConfig = codeContext.getGlobalConfig();
        objectMap.put("sys_author", globalConfig.getAuthor());
        objectMap.put("sys_swagger2", globalConfig.isSwagger2());
        objectMap.put("sys_module_name", globalConfig.getModuleName());

        objectMap.put("sys_entityName", outputInfo.getEntityName());
        objectMap.put("sys_output", outputInfo);

        // ==

        //        log.info(JsonUtils.toString(objectMap, false));
        return objectMap;
    }

}
