package com.kylinhunter.plat.commons.util.rules;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-10 15:07
 **/
public class NamingConvertors {
    private static NamingConvertor[] namingConvertors = new NamingConvertor[NCStrategy.values().length];

    static {
        namingConvertors[NCStrategy.UNDERLINE_TO_CAMEL.ordinal()] = new NCUnderlineToCamel();
        namingConvertors[NCStrategy.UNDERLINE_TO_CAMEL_LOWERCASE_FIRST.ordinal()] =
                new NCUnderlineToCamelLowercaseFirst();
        namingConvertors[NCStrategy.CAMEL_TO_UNDERLINE.ordinal()] = new NCCamelToUnderline();
    }

    public static String convert(NCStrategy ncStrategy, String name) {
        return namingConvertors[ncStrategy.ordinal()].convert(name);
    }

    /**
     * @description
     * @author BiJi'an
     * @date   2022-01-10 14:39
     **/
    public static interface NamingConvertor {
        String convert(String name);
    }

}
