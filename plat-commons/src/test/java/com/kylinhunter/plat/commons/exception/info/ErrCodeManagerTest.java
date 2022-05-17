package com.kylinhunter.plat.commons.exception.info;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.kylinhunter.plat.commons.exception.info.ErrCodeManager;

class ErrCodeManagerTest {

    @Test
    void test() {
        ErrCodeManager.println();

        Assertions.assertEquals("UNKNOWN", ErrCodeManager.getDefaultMsg(100000001));
        Assertions.assertEquals("FORMAT", ErrCodeManager.getDefaultMsg(100000002));
        Assertions.assertEquals("INIT", ErrCodeManager.getDefaultMsg(100000003));
        Assertions.assertEquals("INTERNAL", ErrCodeManager.getDefaultMsg(100000004));
        Assertions.assertEquals("IO", ErrCodeManager.getDefaultMsg(100000005));
        Assertions.assertEquals("PARAM", ErrCodeManager.getDefaultMsg(100000006));
        Assertions.assertEquals("BIZ", ErrCodeManager.getDefaultMsg(100010001));
        Assertions.assertEquals("DB", ErrCodeManager.getDefaultMsg(100020001));
        Assertions.assertEquals("DB_NO_EXIST", ErrCodeManager.getDefaultMsg(100020002));
    }

}