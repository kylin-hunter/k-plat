package com.kylinhunter.plat.generator.core.engine;

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
import com.kylinhunter.plat.commons.util.JsonUtils;
import com.kylinhunter.plat.commons.util.date.DateUtils;
import com.kylinhunter.plat.generator.core.configuration.CodeContext;
import com.kylinhunter.plat.generator.core.configuration.GlobalConfig;
import com.kylinhunter.plat.generator.core.configuration.PackageConfig;
import com.kylinhunter.plat.generator.core.configuration.StrategyConfig;
import com.kylinhunter.plat.generator.core.configuration.StrategyConfigs;
import com.kylinhunter.plat.generator.core.configuration.Template;
import com.kylinhunter.plat.generator.core.configuration.TemplateConfig;
import com.kylinhunter.plat.generator.core.configuration.TemplateType;
import com.kylinhunter.plat.generator.core.configuration.bean.OutputInfo;

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
     * @param CodeContext CodeContext
     * @return com.kylinhunter.plat.generator.custom.AbstractTemplateEngine
     * @title 模板引擎初始化
     * @description
     * @author BiJi'an
     * @date 2021/8/4 8:15 下午
     */
    public AbstractTemplateEngine init(CodeContext CodeContext) {
        this.codeContext = CodeContext;
        return this;
    }

    /**
     * @return com.kylinhunter.plat.generator.custom.AbstractTemplateEngine
     * @title 创建目录
     * @description
     * @author BiJi'an
     * @date 2021/8/4 8:14 下午
     */
    public AbstractTemplateEngine mkdirs() {
        GlobalConfig globalConfig = codeContext.getGlobalConfig();
        Set<Path> allPaths = codeContext.getAllPackagePath();
        allPaths.forEach(path -> {
            if (Files.exists(path) && globalConfig.isClearBeforExec()) {
                FileUtils.deleteQuietly(path.toFile());
                log.info("清空目录=>{}", path);
            }
        });
        allPaths.forEach(path -> {
            if (!Files.exists(path)) {
                try {
                    Files.createDirectories(path);
                    log.info("创建目录=>{}", path);
                } catch (IOException e) {
                    throw new InternalException("创建目录失败", e);
                }
            }
        });

        return this;
    }

    /**
     * @return com.kylinhunter.plat.generator.custom.AbstractTemplateEngine
     * @title 输出结果
     * @description
     * @author BiJi'an
     * @date 2021/8/4 2:23 下午
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
     * @param path path
     * @return java.lang.String
     * @title 模板真实文件路径
     * @description
     * @author BiJi'an
     * @date 2021/8/5 1:02 上午
     */
    public abstract String pathTemplate(String path);

    /**
     * @return java.lang.String
     * @title 文件后缀
     * @description
     * @author BiJi'an
     * @date 2021/8/5 1:02 上午
     */
    protected String fileSuffix() {
        return StringPool.DOT_JAVA;
    }

    /**
     * @param path path
     * @return boolean
     * @title 检测文件是否需要生成
     * @description
     * @author BiJi'an
     * @date 2021/8/5 12:42 上午
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
        codeContext.getGlobalConfig().getOutputDirs().values().stream().distinct()
                .forEach(dir -> this.open(dir.toString()));

    }

    /**
     * @return void
     * @title 打开生成的代码目录
     * @description
     * @author BiJi'an
     * @date 2021/8/4 8:14 下午
     */
    public void open(String outputDir) {
        if (codeContext.getGlobalConfig().isOpen()
                && StringUtils.isNotBlank(outputDir)) {
            try {
                String osName = System.getProperty("os.className");
                if (osName != null) {
                    if (osName.contains("Mac")) {
                        Runtime.getRuntime().exec("open " + outputDir);
                    } else if (osName.contains("Windows")) {
                        Runtime.getRuntime().exec("cmd /c start " + outputDir);
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
     * @param outputInfo outputInfo
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @title 渲染对象 MAP 信息
     * @description
     * @author BiJi'an
     * @date 2021/8/4 8:23 下午
     */
    private Map<String, Object> getObjectMap(OutputInfo outputInfo, Template template) {
        Map<String, Object> objectMap = Maps.newLinkedHashMap();

        objectMap.put("package", outputInfo.getPackageName());
        objectMap.put("imports", outputInfo.getImportPackages());
        objectMap.put("class_name", outputInfo.getClassName());
        objectMap.put("class_comment", outputInfo.getClassName());
        objectMap.put("date", DateUtils.formatDate());
        objectMap.put("entity_fields", outputInfo.getEntityFields());
        String entityName = outputInfo.getEntityName();
        objectMap.put("entity_name", entityName);
        objectMap.put("output", outputInfo);

        final TemplateType templateType = template.getType();
        objectMap.put("template_type", templateType);

        StrategyConfigs strategyConfigs = codeContext.getStrategyConfigs();
        StrategyConfig strategyConfig = strategyConfigs.get(template);
        objectMap.put("strategy_is_lombok", strategyConfig.isLombok());
        objectMap.put("strategy_is_lombok_chain_model", strategyConfig.isLombokChainModel());
        objectMap.put("strategy_has_super_class", StringUtils.isNotBlank(strategyConfig.getSuperClassName()));
        objectMap.put("strategy_super_class", strategyConfig.getSuperClassName());
        objectMap.put("strategy_super_class_name", strategyConfig.getSuperClassSimpleName());
        objectMap.put("strategy_is_serializable", strategyConfig.isSerializable());

        objectMap.put("import_entity_class", outputInfo.getEntityClass().getCanonicalName());
        objectMap.put("import_mapper_class", outputInfo.getMapperClass().getCanonicalName());

        PackageConfig pkgConfig = codeContext.getPackageConfig();

        if (templateType == TemplateType.SERVICE || templateType == TemplateType.CONTROLLER) {

            StrategyConfig strategyConfigVoCreate = strategyConfigs.get(Template.VO_CREATE);
            StrategyConfig strategyConfigVoUpdate = strategyConfigs.get(Template.VO_UPDATE);
            StrategyConfig strategyConfigVoQuery = strategyConfigs.get(Template.VO_REQ_QUREY);
            StrategyConfig strategyConfigVoResponse = strategyConfigs.get(Template.VO_RESPONSE);
            StrategyConfig strategyConfigServiceImp = strategyConfigs.get(Template.SERVICE_LOCAL);

            objectMap.put("import_vo_create", pkgConfig.getImport(strategyConfigVoCreate, entityName));
            objectMap.put("import_vo_update", pkgConfig.getImport(strategyConfigVoUpdate, entityName));
            objectMap.put("import_vo_response", pkgConfig.getImport(strategyConfigVoResponse, entityName));
            objectMap.put("import_vo_query", pkgConfig.getImport(strategyConfigVoQuery, entityName));
            objectMap.put("import_service_local", pkgConfig.getImport(strategyConfigServiceImp, entityName));

            objectMap.put("vo_create_class_name", strategyConfigVoCreate.getClassName(entityName));
            objectMap.put("vo_update_class_name", strategyConfigVoUpdate.getClassName(entityName));
            objectMap.put("vo_resp_class_name", strategyConfigVoResponse.getClassName(entityName));
            objectMap.put("vo_query_class_name", strategyConfigVoQuery.getClassName(entityName));
            objectMap.put("service_local_class_name", strategyConfigServiceImp.getClassName(entityName));

            objectMap.put("pgk_parent", pkgConfig.getParentPackage());
        }

        GlobalConfig globalConfig = codeContext.getGlobalConfig();
        objectMap.put("sys_author", globalConfig.getAuthor());
        objectMap.put("sys_is_swagger2", globalConfig.isSwagger2());
        objectMap.put("sys_module_name", globalConfig.getModuleName());

        log.info(JsonUtils.toString(objectMap, false));
        return objectMap;
    }

}
