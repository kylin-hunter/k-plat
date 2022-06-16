package com.kylinhunter.plat.generator.auto.kplat.core;

import com.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForTenantRole;
import com.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForTenantUser;
import com.kylinhunter.plat.generator.common.Module;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-04 10:53
 **/
public class KPlatCodeGenneratorForTenantRole extends CoreKPlatCodeGennerator {

    @Override
    public Module initModule() {
        return new MybatisPlusGeneratorForTenantRole().getConfig().getModule();
    }

    public static void main(String[] args) {
        CoreKPlatCodeGennerator coreKPlatCodeGennerator = new KPlatCodeGenneratorForTenantRole();
        coreKPlatCodeGennerator.exec(true);
    }
}
