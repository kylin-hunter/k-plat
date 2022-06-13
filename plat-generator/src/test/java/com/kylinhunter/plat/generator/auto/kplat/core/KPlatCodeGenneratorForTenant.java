package com.kylinhunter.plat.generator.auto.kplat.core;

import com.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForTenant;
import com.kylinhunter.plat.generator.common.Module;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
public class KPlatCodeGenneratorForTenant extends CoreKPlatCodeGennerator {

    @Override
    public Module initModule() {
        MybatisPlusGeneratorForTenant mybatisPlusGeneratorForTenant = new MybatisPlusGeneratorForTenant();
        return mybatisPlusGeneratorForTenant.getConfig().getModule();
    }

    public static void main(String[] args) {
        CoreKPlatCodeGennerator platCodeGennerator = new KPlatCodeGenneratorForTenant();
        platCodeGennerator.exec();
        platCodeGennerator.copy();
    }
}
