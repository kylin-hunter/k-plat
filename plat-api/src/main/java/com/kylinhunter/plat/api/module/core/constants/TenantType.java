package com.kylinhunter.plat.api.module.core.constants;

import com.kylinhunter.plat.commons.util.EnumUtil;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-07 16:34
 **/
@Getter
@RequiredArgsConstructor
public enum TenantType implements EnumUtil.EnumCode {
    DEFAULT(0, "DEFAULT"),
    SYS(1, "SYS");
    private final int code;
    private final String name;

    public static boolean isAdmin(int type) {
        return type == 1;
    }

}
