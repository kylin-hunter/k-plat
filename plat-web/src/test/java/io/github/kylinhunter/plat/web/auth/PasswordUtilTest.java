package io.github.kylinhunter.plat.web.auth;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PasswordUtilTest {

    @Test
    void encode() {

        String text = "bijian";

        String encode1 = PasswordUtil.encode(text);
        System.out.println("encode1:" + encode1);

        String encode2 = PasswordUtil.encode(text);
        System.out.println("encode2:" + encode2);

        assertTrue(PasswordUtil.matches(text, encode1));
        assertTrue(PasswordUtil.matches(text, encode2));
    }
}