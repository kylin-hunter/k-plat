package com.kylinhunter.plat.generator.core;

import com.kylinhunter.plat.generator.common.Module;
import com.kylinhunter.plat.generator.core.configuration.Configurations;
import com.kylinhunter.plat.generator.mybatis.MybatisPlusGeneratorForRole;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
public class KPlatCodeGenneratorForRole {

    public static void main(String[] args) {
        Module module = MybatisPlusGeneratorForRole.getConfig().getModule();
        KPlatCodeGennerator kPlatCodeGennerator = new KPlatCodeGennerator();
        Configurations config = DefaultCodeGenneratorConfigHelper.getConfig(module);
        kPlatCodeGennerator.withConfigurations(config).execute();

    }

}
