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
public enum UserType implements EnumUtils.EnumCode {
    USER(0, "USER"),
    SUPER_ADMIN(1, "SUPER_ADMIN"),
    TENANT_USER(2, "TENANT_USER"),
    TENANT_ADMIN(3, "TENANT_ADMIN");
    private final int code;
    private final String name;

    public static boolean isSuperAdmin(int type) {
        return type == 1;
    }

    public static boolean isTenantAdmin(int type) {
        return type == 3;
    }

}
