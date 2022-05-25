package com.kylinhunter.plat.generator.core;

import java.lang.reflect.Field;
import java.util.Arrays;

import com.kylinhunter.plat.api.bean.entity.BaseEntity;
import com.kylinhunter.plat.api.bean.vo.response.single.DefaultSysResp;
import com.kylinhunter.plat.api.bean.vo.request.Req;
import com.kylinhunter.plat.api.bean.vo.create.ReqCreate;
import com.kylinhunter.plat.api.bean.vo.update.ReqUpdate;
import com.kylinhunter.plat.generator.core.configuration.ConfigurationsCustomize;
import com.kylinhunter.plat.generator.core.configuration.StrategyConfig;
import com.kylinhunter.plat.generator.core.configuration.StrategyConfigs;
import com.kylinhunter.plat.generator.core.configuration.Template;
import com.kylinhunter.plat.generator.core.configuration.TemplateConfig;
import com.kylinhunter.plat.generator.core.configuration.TemplateType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-01 16:00
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class ConfigurationsForDefaultService extends ConfigurationsForDefault {
    public ConfigurationsForDefaultService(@NonNull ConfigurationsCustomize configurationsCustom) {
        super(configurationsCustom);

    }

    @Override
    public void initStrategyConfig(StrategyConfigs strategyConfigs) {
        Arrays.stream(Template.values()).filter(t -> t.getType() == TemplateType.SERVICE).forEach(t -> {
            StrategyConfig strategyConfig = strategyConfigs.getStrategyConfig(t);
            strategyConfig.setLombok(true);
            strategyConfig.setLombokChainModel(true);
            strategyConfig.setSuperClass(Req.class);
            if (t == Template.VO_CREATE) {
                strategyConfig.setSuperClass(ReqCreate.class);
            } else if (t == Template.VO_UPDATE) {
                strategyConfig.setSuperClass(ReqUpdate.class);
            } else if (t == Template.VO_RESPONSE) {
                strategyConfig.setSuperClass(DefaultSysResp.class);
            }
            strategyConfig.setSerializable(true);
            for (Field field : BaseEntity.class.getDeclaredFields()) {
                strategyConfig.addSkipField(field.getName());
            }
        });
    }

    @Override
    public void initTemplateConfig(TemplateConfig templateConfig) {
        super.initTemplateConfig(templateConfig);
        templateConfig.setEnabled(TemplateType.SERVICE, false);
    }

}
