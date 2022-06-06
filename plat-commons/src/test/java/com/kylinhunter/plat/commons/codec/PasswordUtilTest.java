package com.kylinhunter.plat.commons.codec;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class PasswordUtilTest {

    @Test
    void encode() {

        String text = "bijian";


        String encode1 = PasswordUtil.encode(text);
        System.out.println("encode1:" + encode1);

        String encode2 = PasswordUtil.encode(text);
        System.out.println("encode2:" + encode2);

        Assertions.assertTrue(PasswordUtil.matches(text, encode1));
        Assertions.assertTrue(PasswordUtil.matches(text, encode2));
    }
}