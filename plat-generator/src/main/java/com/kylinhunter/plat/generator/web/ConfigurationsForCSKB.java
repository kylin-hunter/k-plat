package com.kylinhunter.plat.generator.web;

import com.kylinhunter.plat.generator.web.configuration.ConfigurationsAdapter;
import com.kylinhunter.plat.generator.web.configuration.ConfigurationsCallback;
import com.kylinhunter.plat.generator.web.configuration.GlobalConfig;
import com.kylinhunter.plat.generator.web.configuration.PackageConfig;
import com.kylinhunter.plat.generator.web.configuration.Template;
import com.kylinhunter.plat.generator.web.configuration.TemplateConfig;
import com.kylinhunter.plat.generator.web.configuration.TemplateType;
import com.kylinhunter.plat.commons.io.file.UserDirUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-01 16:00
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public abstract class ConfigurationsForCSKB extends ConfigurationsAdapter {
    public ConfigurationsForCSKB(@NonNull ConfigurationsCallback configurationsCustom) {
        super(configurationsCustom);
    }

    @Override
    public void initGlobalConfig(GlobalConfig globalConfig) {
        //        globalConfig.setOutputDir(UserDirUtils.getDirTmp("src-cskb").getAbsolutePath());
        globalConfig.setAuthor("biji'an");
        globalConfig.setOpen(false);
        globalConfig.setSwagger2(true);
        globalConfig.setFileOverride(true);
        globalConfig.setClearBeforExec(false);
        globalConfig.setAutoCreateOutputDir(true);
        globalConfig.setOutputDir(Template.VO_CREATE, UserDirUtils.getDir("cskb-api/src/main/java"));
        globalConfig.setOutputDir(Template.VO_UPDATE, UserDirUtils.getDir("cskb-api/src/main/java"));
        globalConfig.setOutputDir(Template.VO_RESPONSE, UserDirUtils.getDir("cskb-api/src/main/java"));
        globalConfig.setOutputDir(Template.VO_REQ_QUREY, UserDirUtils.getDir("cskb-api/src/main/java"));
        globalConfig.setOutputDir(Template.SERVICE_LOCAL, UserDirUtils.getDir("cskb-api/src/main/java"));
        globalConfig.setOutputDir(Template.SERVICE_RPC, UserDirUtils.getDir("cskb-api/src/main/java"));
        //        globalConfig.setOutputDir(Template.SERVICE_LOCAL_IMP, UserDirUtils.getDir
        //        ("cskb-api/src/main/java"));
        //        globalConfig.setOutputDir(Template.SERVICE_RPC_IMP, UserDirUtils.getDir
        //        ("cskb-api/src/main/java"));
        globalConfig.setOutputDir(Template.CONTROLLER, UserDirUtils.getDir("cskb-server-gateway/src/main/java"));
    }

    @Override
    public void initPackageConfig(PackageConfig packageConfig) {
        packageConfig.setParentPattern("com.kylinhunter.plat");
        packageConfig.setPackagePattern(Template.VO_CREATE, "api.module.%s.bean.vo");
        packageConfig.setPackagePattern(Template.VO_UPDATE, "api.module.%s.bean.vo");
        packageConfig.setPackagePattern(Template.VO_RESPONSE, "api.module.%s.bean.vo");
        packageConfig.setPackagePattern(Template.VO_REQ_QUREY, "api.module.%s.bean.vo");
        packageConfig.setPackagePattern(Template.SERVICE_LOCAL, "api.module.%s.service.local");
        packageConfig.setPackagePattern(Template.SERVICE_RPC, "api.module.%s.service.rpc");
        packageConfig.setPackagePattern(Template.SERVICE_LOCAL_IMP, "%s.service.local");
        packageConfig.setPackagePattern(Template.SERVICE_RPC_IMP, "%s.service.rpc");
        packageConfig.setPackagePattern(Template.CONTROLLER, "gateway.module.%s.controller");

    }

    @Override
    public void initTemplateConfig(TemplateConfig templateConfig) {
        templateConfig.setEnabled(TemplateType.VO, false);
        templateConfig.setEnabled(TemplateType.SERVICE, false);
        templateConfig.setEnabled(TemplateType.CONTROLLER, false);
    }

}
