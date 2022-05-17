package com.kylinhunter.plat.commons.util.name;


import com.kylinhunter.plat.commons.util.rules.NCStrategy;
import com.kylinhunter.plat.commons.util.rules.NamingConvertors;

import jodd.util.StringPool;
import jodd.util.StringUtil;

/**
 * @description
 * @author BiJi'an
 * @date   2022-01-21 19:55
 **/
public class NamePairUtils {

    public static NamePair toNamePair(String str) {
        if (!StringUtil.isEmpty(str)) {
            NamePair namePair = new NamePair();
            if (str.indexOf(StringPool.UNDERSCORE) > 0) {
                namePair.setUnderline(str);
                namePair.setCamel(NamingConvertors.convert(NCStrategy.UNDERLINE_TO_CAMEL_LOWERCASE_FIRST, str));
            } else {
                namePair.setCamel(str);
                namePair.setUnderline(NamingConvertors.convert(NCStrategy.CAMEL_TO_UNDERLINE, str));
            }

            return namePair;
        }
        return null;
    }

}
