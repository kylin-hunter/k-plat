package com.kylinhunter.plat.commons.exception.explain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.commons.exception.common.KRuntimeException;
import com.kylinhunter.plat.commons.exception.info.ErrInfo;
import com.kylinhunter.plat.commons.exception.info.ErrInfos;
import com.kylinhunter.plat.commons.exception.inner.biz.BizException;

public class ExceptionConverterTest {
    public static ExceptionConverter exceptionConverter = new ExceptionConverter();
    public static ErrInfo ERR_INFO_TEST = new ErrInfo(-99, -99, "default msg");

    @BeforeAll
    static void init() {
        exceptionConverter.register(TestException2.class, e -> {
            ExceptionExplain exceptionExplain = new ExceptionExplain(ERR_INFO_TEST, "TestException2's msg");
            exceptionExplain.setExtra("TestException2's extra");
            return exceptionExplain;
        });

    }

    public static class TestException1 extends BizException {
        public TestException1(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class TestException2 extends RuntimeException {
        public TestException2(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @Test
    void convert() {

        TestException1 testException1 = new TestException1("TestException1's msg", new RuntimeException());
        testException1.setExtra("TestException1's extra");

        TestException2 testException2 = new TestException2("test2", new RuntimeException());

        KRuntimeException convert = exceptionConverter.convert(testException1, true);

        Assertions.assertEquals(TestException1.class, convert.getClass());
        Assertions.assertEquals(ErrInfos.BIZ, convert.getErrInfo());
        Assertions.assertEquals("TestException1's extra", convert.getExtra());
        Assertions.assertEquals("TestException1's msg", convert.getMessage());

        Assertions.assertNotNull(convert.getCause());
        convert = exceptionConverter.convert(testException1, false);

        Assertions.assertEquals(TestException1.class, convert.getClass());
        Assertions.assertEquals(ErrInfos.BIZ, convert.getErrInfo());
        Assertions.assertEquals("TestException1's extra", convert.getExtra());
        Assertions.assertEquals("TestException1's msg", convert.getMessage());

        Assertions.assertNotNull(convert.getCause());

        convert = exceptionConverter.convert(testException2, true);

        Assertions.assertEquals(KRuntimeException.class, convert.getClass());
        Assertions.assertEquals(ERR_INFO_TEST, convert.getErrInfo());
        Assertions.assertEquals("TestException2's extra", convert.getExtra());
        Assertions.assertEquals("TestException2's msg", convert.getMessage());
        Assertions.assertNotNull(convert.getCause());

        convert = exceptionConverter.convert(testException2, false);

        Assertions.assertEquals(KRuntimeException.class, convert.getClass());
        Assertions.assertEquals(ERR_INFO_TEST, convert.getErrInfo());
        Assertions.assertEquals("TestException2's extra", convert.getExtra());
        Assertions.assertEquals("TestException2's msg", convert.getMessage());
        Assertions.assertNull(convert.getCause());

    }

}