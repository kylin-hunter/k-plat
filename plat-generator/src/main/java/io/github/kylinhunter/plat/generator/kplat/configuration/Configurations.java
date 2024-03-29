package io.github.kylinhunter.plat.generator.kplat.configuration;

import java.lang.reflect.Field;
import java.util.Arrays;

import io.github.kylinhunter.plat.api.bean.entity.BaseEntity;
import io.github.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import io.github.kylinhunter.plat.api.bean.vo.query.ReqPage;
import io.github.kylinhunter.plat.api.bean.vo.request.Req;
import io.github.kylinhunter.plat.api.bean.vo.response.single.DefaultSysResp;
import io.github.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import io.github.kylinhunter.plat.generator.common.GenConst;
import io.github.kylinhunter.plat.generator.common.Module;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-01 16:00
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class Configurations {
    private CodeContext codeContext = new CodeContext();

    private Module module;

    public Configurations(Module module) {
        this.module = module;

        module.loadClasses();

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
        globalConfig.setModuleName(module.getName());

    }

    public void initPackageConfig(PackageConfig packageConfig) {
        packageConfig.setParentPattern("io.github.kylinhunter.plat");
        packageConfig.setPackagePattern(Template.VO_CREATE, "api.module.%s.bean.vo");
        packageConfig.setPackagePattern(Template.VO_UPDATE, "api.module.%s.bean.vo");
        packageConfig.setPackagePattern(Template.VO_RESPONSE, "api.module.%s.bean.vo");
        packageConfig.setPackagePattern(Template.VO_REQ_QUREY, "api.module.%s.bean.vo");
        packageConfig.setPackagePattern(Template.VO, "api.module.%s.bean.vo");

        packageConfig.setPackagePattern(Template.SERVICE_LOCAL, "%s.service.local");
        //        packageConfig.setPackagePattern(Template.SERVICE_RPC, "api.module.%s.service.rpc");
        packageConfig.setPackagePattern(Template.SERVICE_LOCAL_IMP, "%s.service.local.imp");
        //        packageConfig.setPackagePattern(Template.SERVICE_RPC_IMP, "%s.service.rpc");

        packageConfig.setPackagePattern(Template.SERVICE_INTERCEPTOR_SAVE_UPDATE, "%s.service.local.interceptor");
        packageConfig.setPackagePattern(Template.SERVICE_INTERCEPTOR_DELETE, "%s.service.local.interceptor");


        packageConfig.setPackagePattern(Template.CONTROLLER, "%s.controller");

    }

    public void initTemplateConfig(TemplateConfig templateConfig) {
        templateConfig.setEnabled(TemplateType.VO, true);
        templateConfig.setEnabled(TemplateType.SERVICE, true);
        templateConfig.setEnabled(TemplateType.CONTROLLER, true);
    }

    public void initStrategyConfig(StrategyConfigs strategyConfigs) {
        strategyConfigs.setEntityClasses(module.getEntityClasses());
        strategyConfigs.setMapperClasses(module.getMapperClasses());

        Arrays.stream(Template.values()).forEach(template -> {
            if (template.getType() == TemplateType.VO) {
                StrategyConfig strategyConfig = strategyConfigs.get(template);
                strategyConfig.setLombok(true);
                strategyConfig.setSuperClassName(Req.class);
                if (template == Template.VO_CREATE) {
                    strategyConfig.setSuperClassName(ReqCreate.class);
                } else if (template == Template.VO_UPDATE) {
                    strategyConfig.setSuperClassName(ReqUpdate.class);
                } else if (template == Template.VO_RESPONSE) {
                    strategyConfig.setSuperClassName(DefaultSysResp.class);
                } else if (template == Template.VO_REQ_QUREY) {
                    strategyConfig.setSuperClassName(ReqPage.class);
                    strategyConfig.setFiledSwagger2(false);
                } else if (template == Template.VO) {

                }
                strategyConfig.setSerializable(true);
                for (Field field : BaseEntity.class.getDeclaredFields()) {
                    strategyConfig.addSkipField(field.getName());
                }
            }

        });
    }

}
