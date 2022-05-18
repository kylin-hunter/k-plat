package com.kylinhunter.plat.commons.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import lombok.Getter;

class EnumServicesTest {

    @Test
    void test() {
        EnumServices.register(TestEnumA.A1, TestServiceA1.class);
        EnumServices.register(TestEnumA.A2, TestServiceA2.class);

        Assertions.assertEquals(TestServiceA1.class, EnumServices.get(TestEnumA.A1).getClass());
        Assertions.assertEquals(TestServiceA2.class, EnumServices.get(TestEnumA.A2).getClass());
        Object serviceA11 = EnumServices.get(TestEnumA.A1);
        Object serviceA12 = EnumServices.get(TestEnumA.A1);
        Assertions.assertSame(serviceA11, serviceA12);

        Assertions.assertEquals(TestServiceB1.class, EnumServices.get(TestEnumB.B1).getClass());
        Assertions.assertEquals(TestServiceB2.class, EnumServices.get(TestEnumB.B2).getClass());
        Object serviceB11 = EnumServices.get(TestEnumB.B1);
        Object serviceB12 = EnumServices.get(TestEnumB.B1);
        Assertions.assertSame(serviceB11, serviceB12);
    }

    public enum TestEnumA {
        A1, A2
    }

    public interface TestService {

    }

    public static class TestServiceA1 implements TestService {

    }

    public static class TestServiceA2 implements TestService {

    }

    public enum TestEnumB implements EnumService {
        B1(TestServiceB1.class),
        B2(TestServiceB2.class);

        @Getter
        public Class<?> clazz;

        TestEnumB(Class<?> clazz) {
            this.clazz = clazz;
        }
    }

    public static class TestServiceB1 implements TestService {

    }

    public static class TestServiceB2 implements TestService {

    }
}