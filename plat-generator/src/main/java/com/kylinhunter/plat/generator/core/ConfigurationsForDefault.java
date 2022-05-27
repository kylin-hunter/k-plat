package com.kylinhunter.plat.generator.core;

import java.lang.reflect.Field;
import java.util.Arrays;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.query.ReqQueryPage;
import com.kylinhunter.plat.api.bean.vo.request.Req;
import com.kylinhunter.plat.api.bean.vo.response.single.DefaultSysResp;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import com.kylinhunter.plat.generator.common.GenConst;
import com.kylinhunter.plat.generator.core.configuration.CodeContext;
import com.kylinhunter.plat.generator.core.configuration.ConfigurationsAdapter;
import com.kylinhunter.plat.generator.core.configuration.ConfigurationsCustomize;
import com.kylinhunter.plat.generator.core.configuration.GlobalConfig;
import com.kylinhunter.plat.generator.core.configuration.PackageConfig;
import com.kylinhunter.plat.generator.core.configuration.StrategyConfig;
import com.kylinhunter.plat.generator.core.configuration.StrategyConfigs;
import com.kylinhunter.plat.generator.core.configuration.Template;
import com.kylinhunter.plat.generator.core.configuration.TemplateConfig;
import com.kylinhunter.plat.generator.core.configuration.TemplateType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 16:00
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class ConfigurationsForDefault extends ConfigurationsAdapter {
    public ConfigurationsForDefault(@NonNull ConfigurationsCustomize configurationsCustom) {
        super(configurationsCustom);
    }

    @Override
    public void init(CodeContext codeContext) {
        initGlobalConfig(codeContext.getGlobalConfig());
        initPackageConfig(codeContext.getPackageConfig());
        initTemplateConfig(codeContext.getTemplateConfig());
        initStrategyConfig(codeContext.getStrategyConfigs());

    }

    public void initGlobalConfig(GlobalConfig globalConfig) {
        globalConfig.setAuthor("biji'an");
        globalConfig.setOpen(false);
        globalConfig.setSwagger2(true);
        globalConfig.setFileOverride(true);
        globalConfig.setClearBeforExec(false);
        globalConfig.setAutoCreateOutputDir(true);
        globalConfig.setDefaultOutputDir(GenConst.DEFAULT_OUTPUT);
    }

    public void initPackageConfig(PackageConfig packageConfig) {
        packageConfig.setParentPattern("com.kylinhunter.plat");
        packageConfig.setPackagePattern(Template.VO_CREATE, "api.module.%s.bean.vo");
        packageConfig.setPackagePattern(Template.VO_UPDATE, "api.module.%s.bean.vo");
        packageConfig.setPackagePattern(Template.VO_RESPONSE, "api.module.%s.bean.vo");
        packageConfig.setPackagePattern(Template.VO_REQ_QUREY, "api.module.%s.bean.vo");
        packageConfig.setPackagePattern(Template.SERVICE_LOCAL, "%s.service.local");
//        packageConfig.setPackagePattern(Template.SERVICE_RPC, "api.module.%s.service.rpc");
        packageConfig.setPackagePattern(Template.SERVICE_LOCAL_IMP, "%s.service.local.imp");
//        packageConfig.setPackagePattern(Template.SERVICE_RPC_IMP, "%s.service.rpc");
        packageConfig.setPackagePattern(Template.CONTROLLER, "%s.controller");

    }

    public void initTemplateConfig(TemplateConfig templateConfig) {
        templateConfig.setEnabled(TemplateType.VO, true);
        templateConfig.setEnabled(TemplateType.SERVICE, true);
        templateConfig.setEnabled(TemplateType.CONTROLLER, true);
    }

    public void initStrategyConfig(StrategyConfigs strategyConfigs) {
        Arrays.stream(Template.values()).forEach(template -> {
            if (template.getType() == TemplateType.VO) {
                StrategyConfig strategyConfig = strategyConfigs.get(template);
                strategyConfig.setLombok(true);
                strategyConfig.setLombokChainModel(true);
                strategyConfig.setSuperClass(Req.class);
                if (template == Template.VO_CREATE) {
                    strategyConfig.setSuperClass(ReqCreate.class);
                } else if (template == Template.VO_UPDATE) {
                    strategyConfig.setSuperClass(ReqUpdate.class);
                } else if (template == Template.VO_RESPONSE) {
                    strategyConfig.setSuperClass(DefaultSysResp.class);
                } else if (template == Template.VO_REQ_QUREY) {
                    strategyConfig.setSuperClass(ReqQueryPage.class);
                    strategyConfig.setFiledSwagger2(false);
                }
                strategyConfig.setSerializable(true);
                for (Field field : BaseEntity.class.getDeclaredFields()) {
                    strategyConfig.addSkipField(field.getName());
                }
            }

        });
    }

}
