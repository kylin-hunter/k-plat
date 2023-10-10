package io.github.kylinhunter.plat.generator.auto;

import io.github.kylinhunter.plat.generator.auto.kplat.core.KPlatCodeGenneratorForPermission;
import io.github.kylinhunter.plat.generator.auto.kplat.core.KPlatCodeGenneratorForRolePermission;
import io.github.kylinhunter.plat.generator.auto.kplat.core.KPlatCodeGenneratorForTenantRolePermission;
import io.github.kylinhunter.plat.generator.auto.kplat.core.KPlatCodeGenneratorForTenantUserRole;
import io.github.kylinhunter.plat.generator.auto.kplat.core.KPlatCodeGenneratorForUserRole;
import io.github.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForPermission;

import io.github.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForRolePermission;
import io.github.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForTenantRolePermission;
import io.github.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForTenantUserRole;
import io.github.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForUserRole;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 17:27
 **/
@Slf4j
public class CodeGenerator {

    public static void main(String[] args) {
        new MybatisPlusGeneratorForTenantRolePermission().exec();
        new KPlatCodeGenneratorForTenantRolePermission().exec(true);

    }

}
