package com.kylinhunter.plat.generator.web.configuration;

import java.io.File;
import java.nio.file.Path;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

import jodd.util.StringPool;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @description 包相关的配置项
 * @author BiJi'an
 * @date   2021/8/6
 **/
@Data
@Accessors(chain = true)
public class PackageConfig {
    private static final String DEFAULT_VO = "vo";
    private static final String DEFAULT_SERVICE = "service";
    private static final String DEFAULT_ONTROLLER = "controller";

    private final GlobalConfig globalConfig;
    private String parentPattern = "com.kylinhunter.plat";   /*父包名*/
    private Map<Template, String> packagePatterns = Maps.newHashMap();

    public PackageConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
        setPackagePattern(Template.VO_CREATE, DEFAULT_VO);
        setPackagePattern(Template.VO_UPDATE, DEFAULT_VO);
        setPackagePattern(Template.VO_RESPONSE, DEFAULT_VO);
        setPackagePattern(Template.VO_REQ_QUREY, DEFAULT_VO);
        setPackagePattern(Template.SERVICE_LOCAL, DEFAULT_SERVICE);
        setPackagePattern(Template.SERVICE_LOCAL_IMP, DEFAULT_SERVICE);
        setPackagePattern(Template.SERVICE_RPC, DEFAULT_SERVICE);
        setPackagePattern(Template.SERVICE_RPC_IMP, DEFAULT_SERVICE);
        setPackagePattern(Template.CONTROLLER, DEFAULT_ONTROLLER);
    }

    public void setPackagePattern(Template template, String pkg) {
        if (StringUtils.isNotBlank(pkg)) {
            this.packagePatterns.put(template, pkg);
        }
    }

    /**
     * @param template
     * @return java.lang.String
     * @throws
     * @title 获取包名
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/6 4:31 下午
     */
    public String getPackage(Template template) {
        String parentPackage = String.format(this.parentPattern, globalConfig.getModuleName());
        String currentPackage =
                String.format(this.packagePatterns.get(template), globalConfig.getModuleName());
        String packageName = (parentPackage + StringPool.DOT + currentPackage);
        packageName = packageName.replaceAll("\\.\\.", "\\.");
        return packageName;
    }

    /**
     * @param template
     * @return java.nio.file.Path
     * @throws
     * @title 获取包的路径
     * @description
     * @author BiJi'an
     * @updateTime 2021/8/6 4:31 下午
     */
    public Path getPackagePath(Template template) {
        Path outputDir = globalConfig.getOutputDir(template);
        String packageName = this.getPackage(template);
        String packagePath = packageName.replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
        return outputDir.resolve(packagePath);
    }
}
