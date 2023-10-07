package io.github.kylinhunter.plat.generator.auto;

import io.github.kylinhunter.plat.generator.auto.kplat.core.KPlatCodeGenneratorForPermission;
import io.github.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForPermission;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 17:27
 **/
@Slf4j
public class CodeGenerator {

    public static void main(String[] args) {
        new MybatisPlusGeneratorForPermission().exec();
        new KPlatCodeGenneratorForPermission().exec(false);

    }

}
