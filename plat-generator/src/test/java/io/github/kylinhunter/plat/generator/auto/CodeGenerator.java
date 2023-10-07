package io.github.kylinhunter.plat.generator.auto;

import io.github.kylinhunter.plat.generator.auto.kplat.core.KPlatCodeGenneratorForPermission;
import io.github.kylinhunter.plat.generator.auto.kplat.core.KPlatCodeGenneratorForRolePermission;
import io.github.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForPermission;

import io.github.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForRolePermission;
import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 17:27
 **/
@Slf4j
public class CodeGenerator {

    public static void main(String[] args) {
        new MybatisPlusGeneratorForRolePermission().exec();
        new KPlatCodeGenneratorForRolePermission().exec(false);

    }

}
