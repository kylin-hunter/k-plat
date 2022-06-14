package com.kylinhunter.plat.generator.auto.kplat.core;

import com.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForRole;
import com.kylinhunter.plat.generator.common.Module;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
public class KPlatCodeGenneratorForRole extends CoreKPlatCodeGennerator {

    @Override
    public Module initModule() {
        MybatisPlusGeneratorForRole mybatisPlusGeneratorForRole = new MybatisPlusGeneratorForRole();
        return mybatisPlusGeneratorForRole.getConfig().getModule();
    }

    public static void main(String[] args) {
        CoreKPlatCodeGennerator platCodeGennerator = new KPlatCodeGenneratorForRole();
        platCodeGennerator.exec();
    }
}
