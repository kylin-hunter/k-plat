package io.github.kylinhunter.plat.api.module.core.constants;


import io.github.kylinhunter.commons.util.EnumUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author BiJi'an
 * @description
 * @date 2022-01-07 16:34
 **/
@Getter
@RequiredArgsConstructor
public enum UserStatus implements EnumUtils.EnumCode {
    NORMAL(0, "NORMAL"),
    FROZEN(1, "FROZEN");
    private final int code;
    private final String name;

    public static boolean isAdmin(int type) {
        return type == 1;
    }

}
