package com.kylinhunter.plat.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.commons.exception.common.KRuntimeException;

import lombok.Getter;

class EnumUtilTest {

    @Test
    void testFromCode() {
        TestEnum testEnum = EnumUtil.fromCode(TestEnum.class, 1);
        assertEquals(TestEnum.TEST1, testEnum);
        testEnum = EnumUtil.fromCode(TestEnum.class, 2);
        assertEquals(TestEnum.TEST2, testEnum);

        TestEnum[] testEnums = EnumUtil.fromCode(TestEnum.class, new int[] {1, 2});

        assertEquals(TestEnum.TEST1, testEnums[0]);
        assertEquals(TestEnum.TEST2, testEnums[1]);

    }

    @Test
    void testFromName() {
        TestEnum testEnum = EnumUtil.fromName(TestEnum.class, "TEST1");
        assertEquals(TestEnum.TEST1, testEnum);
        testEnum = EnumUtil.fromName(TestEnum.class, "TEST2");
        assertEquals(TestEnum.TEST2, testEnum);

        TestEnum[] testEnums = EnumUtil.fromName(TestEnum.class, new String[] {"TEST1", "TEST2"});

        assertEquals(TestEnum.TEST1, testEnums[0]);
        assertEquals(TestEnum.TEST2, testEnums[1]);

    }

    @Test
    void testThrows() {

        Assertions.assertThrows(KRuntimeException.class, () -> EnumUtil.fromCode(TestEnum.class, 7, true));

        assertNull(EnumUtil.fromCode(TestEnum.class, 7, false));
        Assertions
                .assertThrows(KRuntimeException.class, () -> EnumUtil.fromCode(TestEnum.class, new int[] {1, 7}, true));
        assertNull(EnumUtil.fromCode(TestEnum.class, new int[] {1, 7}, false));

        Assertions.assertThrows(KRuntimeException.class, () -> EnumUtil.fromName(TestEnum.class, "test", true));

        assertNull(EnumUtil.fromName(TestEnum.class, "test", false));
        Assertions.assertThrows(KRuntimeException.class,
                () -> EnumUtil.fromName(TestEnum.class, new String[] {"TEST1", "TEST21"}, true));
        assertNull(EnumUtil.fromName(TestEnum.class, new String[] {"TEST1", "TEST21"}, false));

    }

    public enum TestEnum implements EnumUtil.EnumCode {
        TEST1(1),

        TEST2(2);

        @Getter
        private final int code;

        TestEnum(int code) {
            this.code = code;
        }

    }
}