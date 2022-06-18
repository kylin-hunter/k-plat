package com.kylinhunter.plat.generator.auto;

import com.kylinhunter.plat.generator.auto.kplat.core.KPlatCodeGenneratorForTenantUser;
import com.kylinhunter.plat.generator.auto.mybatis.core.MybatisPlusGeneratorForTenantUser;

import lombok.extern.slf4j.Slf4j;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-13 17:27
 **/
@Slf4j
public class CodeGenerateForTenantUser {

    public static void main(String[] args) {
        new MybatisPlusGeneratorForTenantUser().exec();
        new KPlatCodeGenneratorForTenantUser().exec(false);

    }

}
