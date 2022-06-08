package com.kylinhunter.plat.generator.auto;

import java.io.IOException;

import com.kylinhunter.plat.generator.auto.core.KPlatCodeGenneratorForRole;
import com.kylinhunter.plat.generator.auto.mybatis.MybatisPlusGeneratorForRole;

/**
 * @author BiJi'an
 * @description
 * @date 2022-06-06 00:07
 **/
public class GenRole {

    public static void main(String[] args) throws IOException {
        MybatisPlusGeneratorForRole.exec();
        KPlatCodeGenneratorForRole.exec();

        //        KPlatCodeGenneratorForRole.copy();

    }

}
